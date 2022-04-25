package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateUserDTO
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.UserRepository
import hu.bme.aut.familyappbackend.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AuthenticationController(private val authService: AuthService, private val userRepository: UserRepository) {



    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun regUser(@Valid @RequestBody regUser: CreateUserDTO): ResponseEntity<*>? {
        val user: User? = userRepository.findUserByEmail(regUser.email)
        if (user != null) {
            return ResponseEntity.badRequest().body("User with this email already exist.")
        }
        return ResponseEntity.ok(authService.save(regUser))
    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(@Valid @RequestBody userL: CreateUserDTO): ResponseEntity<*> {
        val user: User = userRepository.findUserByEmail(userL.email)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(authService.comparePassword(user.password, userL.password)){
            return ResponseEntity.ok(HttpStatus.ACCEPTED)
        }
        return ResponseEntity.badRequest().body("Wrong email or password")
    }
    //TODO JWT, Auth0
}