package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.CreateInviteDTO
import hu.bme.aut.familyappbackend.dto.CreateUserDTO
import hu.bme.aut.familyappbackend.dto.GetUserDTO
import hu.bme.aut.familyappbackend.mapper.InviteMapper
import hu.bme.aut.familyappbackend.mapper.UserMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.Invite
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.InviteRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class UserService(private val userRepository: UserRepository, private val familyRepository: FamilyRepository, private val inviteRepository: InviteRepository, private val shoppingListService: ShoppingListService) {

    private val passwordEncoder = BCryptPasswordEncoder()
    val secret: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)

    fun save(user: CreateUserDTO): GetUserDTO{
        val pw = this.passwordEncoder.encode(user.password)
        val newUser = User(0,user.email, pw)
        newUser.shoppingLists = mutableListOf()
        newUser.lastModTime = Date(System.currentTimeMillis())
        val us = userRepository.save(newUser)
        val userMapper = Mappers.getMapper(UserMapper::class.java)
        return userMapper.convertToDto(us)
    }

    fun comparePassword(pw: String, password: String): Boolean{
        return BCryptPasswordEncoder().matches(password, pw)
    }

    fun getUserByJWT(jwt: String?): User? {
        return try{
            val body = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).body
            userRepository.findUserById(body.issuer.toInt())
        }catch (e: Exception){
            null
        }
    }
    
    fun delete(userID: Int): ResponseEntity<Unit>{
        val user: User = userRepository.findUserById(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val family = user.family
        user.family = null
        if(family?.users != null){
            val fUsers = family.users as MutableList<User>
            if(fUsers.contains(user))
                fUsers.remove(user)
            family.lastModTime = Date(System.currentTimeMillis())
            familyRepository.save(family)
        }
        val invite = user.invite
        if (invite != null) {
            user.invite = null
            inviteRepository.delete(invite)
        }
        if(user.shoppingLists != null){
            val uSLs = user.shoppingLists as MutableList<ShoppingList>
            user.shoppingLists = null
            for(sl in uSLs){
                shoppingListService.removeUser(sl.id, user.id)
            }
        }
        userRepository.save(user)
        userRepository.delete(user)
        return ResponseEntity(HttpStatus.OK)
    }

    fun invite (invite: CreateInviteDTO, sender: User): ResponseEntity<*>{
        val user: User = userRepository.findUserByEmail(invite.email)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val family: Family = familyRepository.findFamilyById(invite.familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(user == sender || sender.family == null || sender.family!!.id != family.id || user.family?.id == sender.family!!.id){
            return ResponseEntity.badRequest().body(HttpStatus.FORBIDDEN)
        }
        if(user.invite != null){
            user.invite!!.lastModTime = Date(System.currentTimeMillis())
            inviteRepository.delete(user.invite!!)
            if(family.invites != null){
                val fInvites = family.invites as MutableList<Invite>
                if(fInvites.contains(user.invite)){
                    fInvites.remove(user.invite)
                    family.invites = fInvites
                    family.lastModTime = Date(System.currentTimeMillis())
                    familyRepository.save(family)
                }
            }
            user.invite = null
            user.lastModTime = Date(System.currentTimeMillis())
        }
        val newInvite = Invite(0, Date(System.currentTimeMillis()), family, user)
        val i = inviteRepository.save(newInvite)
        user.invite = i
        user.lastModTime = Date(System.currentTimeMillis())
        userRepository.save(user)
        var fInvites = mutableListOf<Invite>()
        if(family.invites != null) {
            fInvites = family.invites as MutableList<Invite>
        }
        fInvites.add(i)
        family.invites = fInvites
        family.lastModTime = Date(System.currentTimeMillis())
        familyRepository.save(family)
        val inviteMapper = Mappers.getMapper(InviteMapper::class.java)
        return ResponseEntity.ok(inviteMapper.convertToDto(i))
    }

    fun edit(user: User, u: User): GetUserDTO {
        user.family = u.family
        user.invite = u.invite
        user.shoppingLists = u.shoppingLists
        user.lastModTime = Date(System.currentTimeMillis())
        val us = userRepository.save(user)
        val userMapper = Mappers.getMapper(UserMapper::class.java)
        return userMapper.convertToDto(us)
    }
}