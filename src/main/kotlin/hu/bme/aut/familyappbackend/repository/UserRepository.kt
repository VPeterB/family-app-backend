package hu.bme.aut.familyappbackend.repository

import hu.bme.aut.familyappbackend.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>{
    fun findUserByEmail(email: String): User?
    fun findUserByID(ID: Int): User?
}