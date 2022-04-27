package hu.bme.aut.familyappbackend.repository

import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FamilyRepository : CrudRepository<Family, Int> {
    fun findFamilyById(id: Int): Family?
}