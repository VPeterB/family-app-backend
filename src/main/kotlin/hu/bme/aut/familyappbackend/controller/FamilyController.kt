package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.model.Family
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("\${api.base-path:/familyapp/FamilyApp/1.0.0}")
class FamilyController {

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/family/{familyID}/adduser"]
    )
    fun addUserToFamily(@PathVariable("familyID") familyID: kotlin.String, @Valid @RequestBody userID: kotlin.String):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/family/create/{userID}"]
    )
    fun createFamily(
        @PathVariable("userID") userID: kotlin.String,
        @Valid @RequestBody(required = false) picture: kotlin.ByteArray?
    ):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/family/{familyID}"]
    )
    fun deleteFamily(@PathVariable("familyID") familyID: kotlin.String):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/family/{familyID}"]
    )
    fun editFamily(@PathVariable("familyID") familyID: kotlin.String, @Valid @RequestBody family: Family):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/family/{familyID}"]
    )
    fun getFamily(
        @PathVariable("familyID") familyID: kotlin.String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/family/{familyID}/removeuser"]
    )
    fun removeUserFromFamily(
        @PathVariable("familyID") familyID: kotlin.String, @Valid @RequestBody user: kotlin.String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}