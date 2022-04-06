package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.mapper.ShoppingItemMapper
import hu.bme.aut.familyappbackend.mapper.ShoppingListMapper
import hu.bme.aut.familyappbackend.mapper.UserMapper
import hu.bme.aut.familyappbackend.model.ShoppingItem
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.ShoppingItemRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/shoppinglist/{shoppinglistID}/shoppingitem")
class ShoppingItemController (private val shoppingItemRepository: ShoppingItemRepository, private val shoppingListRepository: ShoppingListRepository){
    @RequestMapping(value = ["/add"], method = [RequestMethod.POST])
    fun addShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody shoppingitem: ShoppingItem): ResponseEntity<Unit>
    {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping( value = ["/{shoppingitemID}"], method = [RequestMethod.DELETE])
    fun deleteShoppingItem(@PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<Unit>
    {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{shoppingitemID}/done"], method = [RequestMethod.PUT])
    fun doneShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int, @PathVariable("shoppingitemID") shoppingitemID: Int): ResponseEntity<Unit>
    {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{shoppingitemID}/undone"], method = [RequestMethod.PUT])
    fun undoneShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int, @PathVariable("shoppingitemID") shoppingitemID: Int): ResponseEntity<Unit>
    {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{shoppingitemID}"], method = [RequestMethod.PUT])
    fun editShoppingItem(@PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int,
                         @Valid @RequestBody shoppingitem: ShoppingItem): ResponseEntity<Unit>
    {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/all"], method = [RequestMethod.GET])
    fun getAllShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*>
    {
        val sList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.badRequest().body("Shopping list with this ID not found.")
        val sListMapper = Mappers.getMapper(ShoppingListMapper::class.java)
        val sListDto = sListMapper.convertToDto(sList)
        return ResponseEntity.ok(sListDto.shoppingItemIDs)
    }

    @RequestMapping(value = ["/{shoppingitemID}"], method = [RequestMethod.GET])
    fun getShoppingItem(@PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*>
    {
        val sItem: ShoppingItem = shoppingItemRepository.findShoppingItemByID(shoppingitemID)?: return ResponseEntity.badRequest().body("Shopping item with this ID not found.")
        val sItemMapper = Mappers.getMapper(ShoppingItemMapper::class.java)
        val sItemDto = sItemMapper.convertToDto(sItem)
        return ResponseEntity.ok(sItemDto)
    }
}