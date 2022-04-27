package hu.bme.aut.familyappbackend.repository

import hu.bme.aut.familyappbackend.model.Family
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FamilyRepository : CrudRepository<Family, Int> {
    fun findFamilyById(id: Int): Family?
}