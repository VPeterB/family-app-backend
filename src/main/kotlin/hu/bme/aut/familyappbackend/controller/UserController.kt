package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateInviteDTO
import hu.bme.aut.familyappbackend.mapper.InviteMapper
import hu.bme.aut.familyappbackend.mapper.UserMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.Invite
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.InviteRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/user")
class UserController (private val userRepository: UserRepository, private val familyRepository: FamilyRepository, private val inviteRepository: InviteRepository){
    @RequestMapping(value = ["/{userID}"], method = [RequestMethod.DELETE])
    fun deleteUser(@PathVariable("userID") userID: Int): ResponseEntity<Unit> {
        val user: User =userRepository.findUserByID(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(userRepository.delete(user))
    }

    @RequestMapping(value = ["/{userID}"], method = [RequestMethod.PUT])
    fun editUser(@PathVariable("userID") userID: Int, @Valid @RequestBody(required = true) userE: User): ResponseEntity<*> {
        userRepository.findUserByID(userID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        if (userID != userE.ID) {
            return ResponseEntity.badRequest().body("UserID not match with the userE's id")
        }
        return ResponseEntity.ok(userRepository.save(userE))
    }

    @RequestMapping(value = ["/{userID}"], method = [RequestMethod.GET])
    fun getUser( @PathVariable("userID") userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val userMapper = Mappers.getMapper(UserMapper::class.java)
        val userDto = userMapper.convertToDto(user)
        return ResponseEntity.ok(userDto)
    }

    @RequestMapping(value = ["/sendinvite"], method = [RequestMethod.PUT])
    fun inviteUser( @Valid @RequestBody(required = true) invite: CreateInviteDTO): ResponseEntity<Unit> {
        val user: User = userRepository.findUserByEmail(invite.email)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val family: Family = familyRepository.findFamilyByID(invite.familyID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val newInvite = Invite(0, family, user)
        val i = inviteRepository.save(newInvite)
        user.invite = i
        userRepository.save(user)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = ["/{userID}/invite"], method = [RequestMethod.GET])
    fun getUserInvite( @PathVariable("userID") userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val inviteId = user.invite?.ID
        if(inviteId != null){
            val invite: Invite = inviteRepository.findInviteByID(inviteId)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
            val inviteMapper = Mappers.getMapper(InviteMapper::class.java)
            val inviteDto = inviteMapper.convertToDto(invite)
            return ResponseEntity.ok(inviteDto)
        }
        return ResponseEntity.ok(HttpStatus.NO_CONTENT)
    }
}