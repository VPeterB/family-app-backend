package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.model.InlineResponse200
import hu.bme.aut.familyappbackend.model.ShoppingItem
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("\${api.base-path:/familyapp/FamilyApp/1.0.0}")
class ShoppingItemController {

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/add"]
    )
    fun addShoppingItem(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String,
        @Valid @RequestBody shoppingitem: ShoppingItem
    ):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}"]
    )
    fun deleteShoppingItem(
        @PathVariable("shoppingitemID") shoppingitemID: kotlin.String,
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
    ):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}/done"]
    )
    fun doneShoppingItem(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String,
        @PathVariable("shoppingitemID") shoppingitemID: kotlin.String
    ):
            ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}/undone"]
    )
    fun undoneShoppingItem(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String,
        @PathVariable("shoppingitemID") shoppingitemID: kotlin.String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}"]
    )
    fun editShoppingItem(
        @PathVariable("shoppingitemID") shoppingitemID: kotlin.String,
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String,
        @Valid @RequestBody shoppingitem: ShoppingItem
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/all"],
        produces = ["*/*"]
    )
    fun getAllShoppingItem(
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
    ): ResponseEntity<List<InlineResponse200>> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/shoppinglist/{shoppinglistID}/shoppingitem/{shoppingitemID}"]
    )
    fun getShoppingItem(
        @PathVariable("shoppingitemID") shoppingitemID: kotlin.String,
        @PathVariable("shoppinglistID") shoppinglistID: kotlin.String
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}