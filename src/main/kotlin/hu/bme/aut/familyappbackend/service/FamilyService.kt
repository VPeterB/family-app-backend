package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.InviteRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class FamilyService (private val familyRepository: FamilyRepository, private val userRepository: UserRepository, private val inviteRepository: InviteRepository) {
    fun save (user: User): Family {
        val users = mutableListOf<User>()
        users.add(user)
        return familyRepository.save(Family(0, users))
    }

    fun addUser(family: Family, user: User): Family {
        val fUsers: MutableList<User> = family.users as MutableList<User>
        fUsers.add(user)
        family.users = fUsers
        val f = familyRepository.save(family)
        user.family = f
        userRepository.save(user)
        return f
    }

    fun delete(family: Family){
        val fUsers = family.users
        if (fUsers != null) {
            for(user in fUsers){
                user.family = null
            }
        }
        val fSLs = family.shoppingLists
        if (fSLs != null) {
            for(sl in fSLs){
                sl.family = null
            }
        }
        val fInvites = family.invites
        if (fInvites != null) {
            for(invite in fInvites){
                inviteRepository.delete(invite)
            }
        }
        familyRepository.delete(family)
    }

    fun removeUser(family: Family, user: User): Family {
        val users: MutableList<User> = family.users as MutableList<User>
        if(users.contains(user)){
            users.remove(user)
            family.users = users
        }
        val f = familyRepository.save(family)
        user.family = null
        userRepository.save(user)
        return f
    }

    fun edit(family: Family, f: Family): Family {
        family.users = f.users
        family.invites = f.invites
        family.shoppingLists = f.shoppingLists
        return familyRepository.save(family)
    }
}