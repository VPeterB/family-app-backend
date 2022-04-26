package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.GetFamilyDTO
import hu.bme.aut.familyappbackend.mapper.FamilyMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.InviteRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class FamilyService (private val familyRepository: FamilyRepository, private val userRepository: UserRepository, private val inviteRepository: InviteRepository) {
    fun save (user: User): GetFamilyDTO {
        val users = mutableListOf<User>()
        users.add(user)
        val f = familyRepository.save(Family(0, users))
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(f) // TODO két fajta idt ad vissza id, ID // TODO aztán egy idő után infinite lista ez is, valami nagyon fura: talán majd így
    }

    fun addUser(family: Family, user: User): GetFamilyDTO? {
        if(family.users == null)
            return null
        val fUsers: MutableList<User> = family.users as MutableList<User>
        fUsers.add(user)
        family.users = fUsers
        val f = familyRepository.save(family)
        user.family = f
        userRepository.save(user)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(f)
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

    fun removeUser(family: Family, user: User): GetFamilyDTO? {
        if(family.users == null){
            return null
        }
        val users: MutableList<User> = family.users as MutableList<User>
        if(users.contains(user)){
            users.remove(user)
            family.users = users
        }
        val f = familyRepository.save(family)
        user.family = null
        userRepository.save(user)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(f)
    }

    fun edit(family: Family, f: Family): GetFamilyDTO {
        family.users = f.users
        family.invites = f.invites
        family.shoppingLists = f.shoppingLists
        val fam = familyRepository.save(family)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return familyMapper.convertToDto(fam)
    }
}