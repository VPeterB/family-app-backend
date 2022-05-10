package hu.bme.aut.familyappbackend.repository

import hu.bme.aut.familyappbackend.model.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : CrudRepository<Event, Int> {
    fun findEventById(id: Int): Event?
}