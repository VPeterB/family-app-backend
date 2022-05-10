package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.GetFamilyDTO
import hu.bme.aut.familyappbackend.mapper.FamilyMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.InviteRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class FamilyService (private val familyRepository: FamilyRepository, private val userRepository: UserRepository, private val inviteRepository: InviteRepository, private val shoppingListRepository: ShoppingListRepository) {
    fun save (user: User): GetFamilyDTO {
        val users = mutableListOf<User>()
        users.add(user)
        val f = familyRepository.save(Family(0, Timestamp(System.currentTimeMillis()), users, mutableListOf(), mutableListOf()))
        user.family = f
        user.lastModTime = Timestamp(System.currentTimeMillis())
        userRepository.save(user)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(f)
    }

    fun addUser(family: Family, user: User): GetFamilyDTO? {
        if(family.users == null)
            return null
        val fUsers: MutableList<User> = family.users as MutableList<User>
        fUsers.add(user)
        family.users = fUsers
        family.lastModTime = Timestamp(System.currentTimeMillis())
        val f = familyRepository.save(family)
        user.family = f
        user.lastModTime = Timestamp(System.currentTimeMillis())
        userRepository.save(user)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(f)
    }

    fun delete(family: Family){
        val fUsers = family.users
        if (fUsers != null) {
            for(user in fUsers){
                user.family = null
                user.lastModTime = Timestamp(System.currentTimeMillis())
                userRepository.save(user)
            }
        }
        val fSLs = family.shoppingLists
        if (fSLs != null) {
            for(sl in fSLs){
                sl.family = null
                sl.lastModTime = Timestamp(System.currentTimeMillis())
                shoppingListRepository.save(sl)
            }
        }
        val fInvites = family.invites
        if (fInvites != null) {
            family.invites = null
            for(invite in fInvites){
                invite.family = null
                if(invite.user != null){
                    invite.user!!.invite = null
                    invite.user!!.lastModTime = Timestamp(System.currentTimeMillis())
                    userRepository.save(invite.user!!)
                }
                invite.user = null
                inviteRepository.save(invite)
                inviteRepository.delete(invite)
            }
        }
        familyRepository.save(family)
        familyRepository.delete(family)
    }

    fun removeUser(family: Family, user: User): GetFamilyDTO? {
        if(family.users == null){
            return null
        }
        val users: MutableList<User> = family.users as MutableList<User>
        if(users.contains(user)){
            users.remove(user)
            family.users = users
        }
        family.lastModTime = Timestamp(System.currentTimeMillis())
        val f = familyRepository.save(family)
        user.family = null
        user.lastModTime = Timestamp(System.currentTimeMillis())
        userRepository.save(user)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(f)
    }

    fun edit(family: Family, f: Family): GetFamilyDTO {
        family.users = f.users
        family.invites = f.invites
        family.shoppingLists = f.shoppingLists
        family.lastModTime = Timestamp(System.currentTimeMillis())
        val fam = familyRepository.save(family)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(fam)
    }
}