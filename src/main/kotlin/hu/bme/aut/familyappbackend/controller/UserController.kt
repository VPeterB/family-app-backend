package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.model.InlineObject
import hu.bme.aut.familyappbackend.model.InlineObject1
import hu.bme.aut.familyappbackend.model.InlineObject2
import hu.bme.aut.familyappbackend.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("\${api.base-path:/familyapp/FamilyApp/1.0.0}")
class UserController {

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/register"]
    )
    fun reg(
        @Valid @RequestBody(required = false) user: InlineObject?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/login"]
    )
    fun login(
        @Valid @RequestBody(required = false) user: InlineObject1?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/user/{userID}"]
    )
    fun deleteUser(@PathVariable("userID") userID: kotlin.String):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/user/{userID}"]
    )
    fun editUser(
        @PathVariable("userID") userID: kotlin.String, @Valid @RequestBody(required = false) user: User?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/user/{userID}"]
    )
    fun getUser(
        @PathVariable("userID") userID: kotlin.String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/user/{userID}/sendinvite"]
    )
    fun inviteUser(
        @PathVariable("userID") userID: kotlin.String, @Valid @RequestBody(required = false) invite: InlineObject2?
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}