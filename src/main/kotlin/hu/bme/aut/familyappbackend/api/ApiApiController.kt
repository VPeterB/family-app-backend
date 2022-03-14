package hu.bme.aut.familyappbackend.api

import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.InlineObject
import hu.bme.aut.familyappbackend.model.InlineObject1
import hu.bme.aut.familyappbackend.model.InlineObject2
import hu.bme.aut.familyappbackend.model.InlineObject3
import hu.bme.aut.familyappbackend.model.InlineResponse200
import hu.bme.aut.familyappbackend.model.ShoppingItem
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import javax.validation.Valid
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

import kotlin.collections.List
import kotlin.collections.Map

@RestController
@Validated
@RequestMapping("\${api.base-path:/familyapp/FamilyApp/1.0.0}")
class ApiApiController() {


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/add"]
    )
    fun addShoppingItem( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
, @Valid @RequestBody shoppingitem: ShoppingItem
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/family/{familyID}/adduser"]
    )
    fun addUserToFamily( @PathVariable("familyID") familyID: kotlin.String
, @Valid @RequestBody userID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/adduser"]
    )
    fun addUserToShoppingList( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
, @Valid @RequestBody userID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/family/create/{userID}"]
    )
    fun createFamily( @PathVariable("userID") userID: kotlin.String
, @Valid @RequestBody(required = false) picture: kotlin.ByteArray?
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/shoppinglist/create/{userID}"]
    )
    fun createShoppingList( @PathVariable("userID") userID: kotlin.String
, @Valid @RequestBody shoppinglistcreate: InlineObject3
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/family/{familyID}"]
    )
    fun deleteFamily( @PathVariable("familyID") familyID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}"]
    )
    fun deleteShoppingItem( @PathVariable("shoppingitemID") shoppingitemID: kotlin.String
, @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/shoppinglist/{shoppinglistID}"]
    )
    fun deleteShoppingList( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/user/{userID}"]
    )
    fun deleteUser( @PathVariable("userID") userID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}/done"]
    )
    fun doneShoppingItem( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
, @PathVariable("shoppingitemID") shoppingitemID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/family/{familyID}"]
    )
    fun editFamily( @PathVariable("familyID") familyID: kotlin.String
, @Valid @RequestBody family: Family
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}"]
    )
    fun editShoppingItem( @PathVariable("shoppingitemID") shoppingitemID: kotlin.String
, @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
, @Valid @RequestBody shoppingitem: ShoppingItem
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}"]
    )
    fun editShoppingList( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
, @Valid @RequestBody shoppinglist: ShoppingList
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/user/{userID}"]
    )
    fun editUser( @PathVariable("userID") userID: kotlin.String
, @Valid @RequestBody(required = false) user: User?
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/all"],
        produces = ["*/*"]
    )
    fun getAllShoppingItem( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
): ResponseEntity<List<InlineResponse200>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/family/{familyID}"]
    )
    fun getFamily( @PathVariable("familyID") familyID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}"]
    )
    fun getShoppingItem( @PathVariable("shoppingitemID") shoppingitemID: kotlin.String
, @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/{shoppinglistID}"]
    )
    fun getShoppingList( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/byfamily/{familyID}"],
        produces = ["*/*"]
    )
    fun getShoppingListbyFamily( @PathVariable("familyID") familyID: kotlin.String
): ResponseEntity<List<InlineResponse200>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/user/{userID}"]
    )
    fun getUser( @PathVariable("userID") userID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/user/{userID}/sendinvite"]
    )
    fun inviteUser( @PathVariable("userID") userID: kotlin.String
, @Valid @RequestBody(required = false) invite: InlineObject2?
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/login"]
    )
    fun login( @Valid @RequestBody(required = false) user: InlineObject1?
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/register"]
    )
    fun reg( @Valid @RequestBody(required = false) user: InlineObject?
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/family/{familyID}/removeuser"]
    )
    fun removeUserFromFamily( @PathVariable("familyID") familyID: kotlin.String
, @Valid @RequestBody user: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/removeuser"]
    )
    fun removeUserFromShoppingList( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
, @Valid @RequestBody userID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}/undone"]
    )
    fun undoneShoppingItem( @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
, @PathVariable("shoppingitemID") shoppingitemID: kotlin.String
): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
