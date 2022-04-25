package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.CreateUserDTO
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun save(user: CreateUserDTO): User{
        val pw = this.passwordEncoder.encode(user.password)
        val newUser = User(0,user.email, pw)
        return userRepository.save(newUser)
    }

    fun comparePassword(pw: String, password: String): Boolean{
        return BCryptPasswordEncoder().matches(password, pw)
    }
}