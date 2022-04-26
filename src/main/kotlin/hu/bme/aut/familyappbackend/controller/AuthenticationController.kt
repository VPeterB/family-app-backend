package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateUserDTO
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.UserRepository
import hu.bme.aut.familyappbackend.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class AuthenticationController(private val userService: UserService, private val userRepository: UserRepository) {

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun regUser(@Valid @RequestBody regUser: CreateUserDTO): ResponseEntity<*>? {
        val user: User? = userRepository.findUserByEmail(regUser.email)
        if (user != null) {
            return ResponseEntity.badRequest().body("User with this email already exist.")
        }
        return ResponseEntity.ok(userService.save(regUser))
    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(@Valid @RequestBody userL: CreateUserDTO, response: HttpServletResponse): ResponseEntity<*> {
        val user: User = userRepository.findUserByEmail(userL.email)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(!userService.comparePassword(user.password, userL.password)){
            return ResponseEntity.badRequest().body("Wrong email or password")
        }
        val issuer = user.ID.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.ES512, userService.secret).compact()
        val cookie = Cookie("jwt", jwt) // TODO jwt cookie
        cookie.isHttpOnly = true
        response.addCookie(cookie)
        return ResponseEntity.ok(HttpStatus.ACCEPTED)
    }
}