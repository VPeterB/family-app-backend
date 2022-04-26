package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.mapper.FamilyMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import hu.bme.aut.familyappbackend.service.FamilyService
import hu.bme.aut.familyappbackend.service.UserService
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/api/family")
class FamilyController (private val familyRepository: FamilyRepository, private val familyService: FamilyService, private val userRepository: UserRepository, private val userService: UserService) {
    @RequestMapping(value = ["/{familyID}/adduser"],method = [RequestMethod.PUT])
    fun addUserToFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(familyService.addUser(family, user)?: return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT))
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST]) //TODO kép // TODO backend picture bytearray-t vár --> meeting: MultipartFile
    fun createFamily(@CookieValue("jwt") jwt: String?, @Valid @RequestBody(required = false) picture: MultipartFile?): ResponseEntity<*> {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val user = userService.getUserByJWT(jwt)?: return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        return ResponseEntity.ok(familyService.save(user))
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.DELETE])
    fun deleteFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<Unit> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(familyService.delete(family))
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.PUT])
    fun editFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody family: Family): ResponseEntity<*> {
        if (familyID != family.ID) {
            return ResponseEntity.badRequest().body("FamilyID not match with the familyE's id")
        }
        val f = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(familyService.edit(family, f))
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.GET])
    fun getFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        return ResponseEntity.ok(familyMapper.convertToDto(family))
    }

    @RequestMapping(value = ["/{familyID}/removeuser"], method = [RequestMethod.PUT])
    fun removeUserFromFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(familyService.removeUser(family, user)?: return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT))
    }
}