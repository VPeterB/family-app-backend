package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.GetEventDTO
import hu.bme.aut.familyappbackend.mapper.EventMapper
import hu.bme.aut.familyappbackend.model.Event
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.EventRepository
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class EventService (private val eventRepository: EventRepository, private val userRepository: UserRepository, private val familyRepository: FamilyRepository, private val userService: UserService){
    fun addUser(event: Event, user: User): Event {
        var eUsers: MutableList<User> = mutableListOf()
        if(event.users != null){
            eUsers= event.users as MutableList<User>
        }
        eUsers.add(user)
        event.users = eUsers
        val e = eventRepository.save(event)
        var uEvents: MutableList<Event> = mutableListOf()
        if(user.events != null){
            uEvents = user.events as MutableList<Event>
        }
        uEvents.add(e)
        user.events = uEvents
        user.lastModTime = Timestamp(System.currentTimeMillis())
        userRepository.save(user)
        return e
    }

    fun delete(event: Event){
        if(event.users != null){
            val users: MutableList<User> = event.users as MutableList<User>
            for(user in users){
                val uEvents = user.events as MutableList<Event>
                if(uEvents.contains(event))
                    uEvents.remove(event)
                user.lastModTime = Timestamp(System.currentTimeMillis())
                userRepository.save(user)
            }
        }
        val family = event.family
        if(family?.events != null){
            val fEvents = family.events as MutableList<Event>
            if(fEvents.contains(event))
                fEvents.remove(event)
            family.lastModTime = Timestamp(System.currentTimeMillis())
            familyRepository.save(family)
        }
        eventRepository.save(event)
        eventRepository.delete(event)
    }

    fun removeUser(eventID: Int, userID: Int): ResponseEntity<Unit> {
        val event: Event = eventRepository.findEventById(eventID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val user: User = userRepository.findUserById(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        if(user.events == null)
            return ResponseEntity(HttpStatus.NOT_MODIFIED)
        if(event.users == null)
            return ResponseEntity(HttpStatus.NOT_MODIFIED)
        val users: MutableList<User> = event.users as MutableList<User>
        if(!users.contains(user))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        users.remove(user)
        event.users = users
        eventRepository.save(event)
        val uEvents: MutableList<Event> = user.events as MutableList<Event>
        if(!uEvents.contains(event))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        uEvents.remove(event)
        user.events = uEvents
        user.lastModTime = Timestamp(System.currentTimeMillis())
        userRepository.save(user)
        return ResponseEntity(HttpStatus.OK)
    }

    fun toDtoList(events: MutableList<Event>): MutableList<GetEventDTO>{
        val rE = mutableListOf<GetEventDTO>()
        val eventMapper = Mappers.getMapper(EventMapper::class.java)
        for(e in events){
            val eventDTO = eventMapper.convertToDto(e)
            rE.add(eventDTO)
        }
        return rE
    }

    fun byFamily(family: Family): MutableList<GetEventDTO>? {
        if(family.events != null){
            val events = family.events as MutableList<Event>
            return toDtoList(events)
        }
        return null
    }

    fun byUser(user: User): MutableList<GetEventDTO>? {
        if (user.events != null){
            val events = user.events as MutableList<Event>
            return toDtoList(events)
        }
        return null
    }

    fun edit(eventE: GetEventDTO, e: Event): GetEventDTO {
        val event = Event(eventE.id, eventE.location, eventE.description, eventE.name, eventE.end, eventE.start)
        event.users = e.users
        event.family = e.family
        val ev = eventRepository.save(event)
        val eventMapper = Mappers.getMapper(EventMapper::class.java)
        return eventMapper.convertToDto(ev)
    }

    fun create(eventCreate: GetEventDTO, user: User): ResponseEntity<*>{
        val newEvent = Event(0, eventCreate.location, eventCreate.description, eventCreate.name, eventCreate.end, eventCreate.start)
        newEvent.users = mutableListOf()
        if(eventCreate.familyID != null){
            val family: Family = familyRepository.findFamilyById(eventCreate.familyID!!)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
            newEvent.family = family
        }
        if(eventCreate.userIDs != null){
            val users = mutableListOf<User>()
            for (id in eventCreate.userIDs!!){
                val userA: User = userRepository.findUserById(id)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
                users.add(userA)
            }
            newEvent.users = users
        }
        val e = addUser(newEvent, user)
        val eventMapper = Mappers.getMapper(EventMapper::class.java)
        return ResponseEntity.ok(eventMapper.convertToDto(e))
    }
}