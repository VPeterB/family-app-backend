package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.mapper.FamilyMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/family")
class FamilyController (private val familyRepository: FamilyRepository, private val userRepository: UserRepository) {
    @RequestMapping(value = ["/{familyID}/adduser"],method = [RequestMethod.PUT])
    fun addUserToFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val users: MutableList<User> = family.users as MutableList<User>
        users.add(user)
        family.users = users
        familyRepository.save(family)
        user.family = family
        userRepository.save(user)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST]) //TODO auth user
    fun createFamily(@Valid @RequestBody(required = false) picture: ByteArray?): ResponseEntity<*> {
        val family = Family(0 /*add owner*/)
        return ResponseEntity.ok(familyRepository.save(family))
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.DELETE])
    fun deleteFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<Unit> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(familyRepository.delete(family))
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.PUT])
    fun editFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody family: Family): ResponseEntity<*> {
        familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if (familyID != family.ID) {
            return ResponseEntity.badRequest().body("FamilyID not match with the familyE's id")
        }
        return ResponseEntity.ok(familyRepository.save(family))
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.GET])
    fun getFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        val familyDto = familyMapper.convertToDto(family)
        return ResponseEntity.ok(familyDto)
    }

    @RequestMapping(value = ["/{familyID}/removeuser"], method = [RequestMethod.PUT])
    fun removeUserFromFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val users: MutableList<User> = family.users as MutableList<User>
        if(!users.contains(user))
            return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        users.remove(user)
        family.users = users
        return ResponseEntity.ok(familyRepository.save(family))
    }
}