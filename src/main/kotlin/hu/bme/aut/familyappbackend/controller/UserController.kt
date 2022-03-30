package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.UserDTO
import hu.bme.aut.familyappbackend.inline.Inv
import hu.bme.aut.familyappbackend.mapper.UserMapper
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/user")
class UserController (private val userRepository: UserRepository){
    @RequestMapping(value = ["/{userID}"], method = [RequestMethod.DELETE])
    fun deleteUser(@PathVariable("userID") userID: Int): ResponseEntity<*> {
        val user: User =userRepository.findUserByID(userID)?: return ResponseEntity.badRequest().body("User with this ID not found.")
        return ResponseEntity.ok(userRepository.delete(user))
    }

    @RequestMapping(value = ["/{userID}"], method = [RequestMethod.PUT])
    fun editUser(@PathVariable("userID") userID: Int, @Valid @RequestBody(required = false) userE: User?): ResponseEntity<*> {
        val user: User? = userRepository.findUserByID(userID)
        if (userE != null) {
            if (user == null || userID != userE.ID) {
                return ResponseEntity.badRequest().body("User with this ID not found. Or userID not match with the userE's id")
            }
            userRepository.delete(user)
            return ResponseEntity.ok(userRepository.save(userE))
        }else{
            return ResponseEntity.badRequest().body("No user in body.")
        }
    }

    @RequestMapping(value = ["/{userID}"], method = [RequestMethod.GET])
    fun getUser( @PathVariable("userID") userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.badRequest().body("User with this ID not found.")
        val userMapper = Mappers.getMapper(UserMapper::class.java)
        val userDto = userMapper.convertToDto(user)
        return ResponseEntity.ok(userDto)
    }

    @RequestMapping(value = ["/sendinvite"], method = [RequestMethod.PUT])
    fun inviteUser( @Valid @RequestBody(required = false) invite: Inv?): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}