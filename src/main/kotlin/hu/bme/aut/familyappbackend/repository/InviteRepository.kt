package hu.bme.aut.familyappbackend.repository

import hu.bme.aut.familyappbackend.model.Invite
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface InviteRepository : CrudRepository<Invite, Int> {
    fun findInviteById(id: Int): Invite?
}