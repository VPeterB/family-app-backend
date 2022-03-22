package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.model.InlineObject3
import hu.bme.aut.familyappbackend.model.InlineResponse200
import hu.bme.aut.familyappbackend.model.ShoppingList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("\${api.base-path:/familyapp/FamilyApp/1.0.0}")
class ShoppingListController {

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/adduser"]
    )
    fun addUserToShoppingList(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String,
        @Valid @RequestBody userID: kotlin.String
    ):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/shoppinglist/create/{userID}"]
    )
    fun createShoppingList(
        @PathVariable("userID") userID: kotlin.String,
        @Valid @RequestBody shoppinglistcreate: InlineObject3
    ):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/shoppinglist/{shoppinglistID}"]
    )
    fun deleteShoppingList(@PathVariable("shoppinglistID") shoppinglistID: kotlin.String):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}"]
    )
    fun editShoppingList(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String, @Valid @RequestBody shoppinglist: ShoppingList
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/{shoppinglistID}"]
    )
    fun getShoppingList(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/byfamily/{familyID}"],
        produces = ["*/*"]
    )
    fun getShoppingListbyFamily(
        @PathVariable("familyID") familyID: kotlin.String
    ): ResponseEntity<List<InlineResponse200>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/removeuser"]
    )
    fun removeUserFromShoppingList(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String, @Valid @RequestBody userID: kotlin.String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}