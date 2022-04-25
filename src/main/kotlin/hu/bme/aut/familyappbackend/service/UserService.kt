package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.CreateInviteDTO
import hu.bme.aut.familyappbackend.dto.CreateUserDTO
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.Invite
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.InviteRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import io.jsonwebtoken.Jwts
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val familyRepository: FamilyRepository, private val inviteRepository: InviteRepository, private val shoppingListService: ShoppingListService) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun save(user: CreateUserDTO): User{
        val pw = this.passwordEncoder.encode(user.password)
        val newUser = User(0,user.email, pw)
        return userRepository.save(newUser)
    }

    fun comparePassword(pw: String, password: String): Boolean{
        return BCryptPasswordEncoder().matches(password, pw)
    }

    fun getUserByJWT(jwt: String?): User? {
        return try{
            val body = Jwts.parser().setSigningKey("secret").parseClaimsJws(jwt).body
            userRepository.findUserByID(body.issuer.toInt())
        }catch (e: Exception){
            null
        }
    }
    
    fun delete(userID: Int): ResponseEntity<Unit>{
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val family = user.family
        val fUsers = family?.users as MutableList<User>
        if(fUsers.contains(user))
            fUsers.remove(user)
        familyRepository.save(family)
        val invite = user.invite
        if (invite != null) {
            inviteRepository.delete(invite)
        }
        val uSLs = user.shoppingLists as MutableList<ShoppingList>
        for(sl in uSLs){
            shoppingListService.removeUser(sl.ID, user.ID)
        }
        userRepository.delete(user)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    fun invite (invite: CreateInviteDTO): ResponseEntity<Unit>{
        val user: User = userRepository.findUserByEmail(invite.email)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val family: Family = familyRepository.findFamilyByID(invite.familyID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val newInvite = Invite(0, family, user)
        val i = inviteRepository.save(newInvite)
        user.invite = i
        userRepository.save(user)
        return ResponseEntity(HttpStatus.OK)
    }
}