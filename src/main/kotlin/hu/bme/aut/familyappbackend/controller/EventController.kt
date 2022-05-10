package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.GetEventDTO
import hu.bme.aut.familyappbackend.mapper.EventMapper
import hu.bme.aut.familyappbackend.model.Event
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.EventRepository
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import hu.bme.aut.familyappbackend.service.EventService
import hu.bme.aut.familyappbackend.service.UserService
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/event")
class EventController (private val eventRepository: EventRepository, private val familyRepository: FamilyRepository, private val userRepository: UserRepository, private val userService: UserService, private val eventService: EventService) {
    @RequestMapping(value = ["/adduser/{eventID}/{userID}"], method = [RequestMethod.PUT])
    fun addUserToEvent(@CookieValue("jwt") jwt: String?, @PathVariable("eventID") eventID: Int, @PathVariable("userID") userID: Int): ResponseEntity<Unit> {
        val event: Event = eventRepository.findEventById(eventID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        if(jwt == null)
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        val user: User = userRepository.findUserById(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        eventService.addUser(event, user)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST])
    fun createEvent(@CookieValue("jwt") jwt: String?, @Valid @RequestBody eventCreate: GetEventDTO): ResponseEntity<*> {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val user = userService.getUserByJWT(jwt)?: return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        return eventService.create(eventCreate, user)
    }

    @RequestMapping(value = ["/{eventID}"], method = [RequestMethod.DELETE])
    fun deleteEvent(@CookieValue("jwt") jwt: String?, @PathVariable("eventID") eventID: Int): ResponseEntity<Unit> {
        val event: Event = eventRepository.findEventById(eventID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        if(jwt == null)
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        return ResponseEntity.ok(eventService.delete(event))
    }

    @RequestMapping(value = ["/{eventID}"], method = [RequestMethod.PUT])
    fun editEvent(@CookieValue("jwt") jwt: String?, @PathVariable("eventID") eventID: Int, @Valid @RequestBody eventE: GetEventDTO): ResponseEntity<*> {
        if (eventID != eventE.id) {
            return ResponseEntity.badRequest().body("EventID not match with the eventE's id")
        }
        val event = eventRepository.findEventById(eventID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(jwt == null)
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        return ResponseEntity.ok(eventService.edit(eventE, event))
    }

    @RequestMapping(value = ["/{eventID}"], method = [RequestMethod.GET])
    fun getEvent(@CookieValue("jwt") jwt: String?, @PathVariable("eventID") eventID: Int): ResponseEntity<*> {
        val event: Event = eventRepository.findEventById(eventID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(jwt == null)
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        val eventMapper = Mappers.getMapper(EventMapper::class.java)
        return ResponseEntity.ok(eventMapper.convertToDto(event))
    }

    @RequestMapping(value = ["/byfamily/{familyID}"], method = [RequestMethod.GET])
    fun getEventByFamily(@CookieValue("jwt") jwt: String?, @PathVariable("familyID") familyID: Int): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyById(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(jwt == null)
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        return ResponseEntity.ok(eventService.byFamily(family)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND))
    }

    @RequestMapping(value = ["/byuser/{userID}"], method = [RequestMethod.GET])
    fun getShoppingListsByUser(@CookieValue("jwt") jwt: String?, @PathVariable("userID") userID: Int): ResponseEntity<*> {
        if(jwt == null)
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        val user: User = userRepository.findUserById(userID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(eventService.byUser(user)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND))
    }

    @RequestMapping(value = ["/removeuser/{eventID}/{userID}"], method = [RequestMethod.PUT])
    fun removeUserFromEvent(@CookieValue("jwt") jwt: String?, @PathVariable("eventID") eventID: Int, @PathVariable("userID") userID: Int): ResponseEntity<Unit> {
        if(jwt == null)
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        return eventService.removeUser(eventID, userID)
    }
}