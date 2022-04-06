package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.mapper.FamilyMapper
import hu.bme.aut.familyappbackend.mapper.UserMapper
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
class FamilyController (private val familyRepository: FamilyRepository) {
    @RequestMapping(value = ["/{familyID}/adduser"],method = [RequestMethod.PUT])
    fun addUserToFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody userID: String): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST])
    fun createFamily(@Valid @RequestBody(required = false) picture: ByteArray?): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.DELETE])
    fun deleteFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.PUT])
    fun editFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody family: Family): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{familyID}"], method = [RequestMethod.GET])
    fun getFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body("Family with this ID not found.")
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        val familyDto = familyMapper.convertToDto(family)
        return ResponseEntity.ok(familyDto)
    }

    @RequestMapping(value = ["/{familyID}/removeuser"], method = [RequestMethod.PUT])
    fun removeUserFromFamily(@PathVariable("familyID") familyID: Int, @Valid @RequestBody userID: Int): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}