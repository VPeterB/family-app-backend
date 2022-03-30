package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.inline.Login
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AuthenticationController(private val userRepository: UserRepository) {
    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun regUser(@Valid @RequestBody regUser: Login): ResponseEntity<*>? {
        val user: User? = userRepository.findUserByEmail(regUser.email)
        if (user != null) {
            return ResponseEntity.badRequest().body("User with this email already exists")
        }
        val newUser = User(0,regUser.email, regUser.password) //TODO valami jobb megoldás
        return ResponseEntity.ok(userRepository.save(newUser))
    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(@Valid @RequestBody userL: Login): ResponseEntity<*> {
        val user: User? = userRepository.findUserByEmail(userL.email)
        if(user != null && user.password == userL.password){
            return ResponseEntity.ok(HttpStatus.ACCEPTED)
        }
        return ResponseEntity.badRequest().body("Wrong email or password")
    }

    //TODO JWT, Oauth, password encode
}