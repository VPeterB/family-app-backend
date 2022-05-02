package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateShoppingItemDTO
import hu.bme.aut.familyappbackend.mapper.ShoppingItemMapper
import hu.bme.aut.familyappbackend.model.ShoppingItem
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.repository.ShoppingItemRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import hu.bme.aut.familyappbackend.service.ShoppingItemService
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Required
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/shoppinglist/{shoppinglistID}/shoppingitem")
class ShoppingItemController (private val shoppingItemRepository: ShoppingItemRepository, private val shoppingListRepository: ShoppingListRepository, private val shoppingItemService: ShoppingItemService){
    @RequestMapping(value = ["/add"], method = [RequestMethod.POST])
    fun addShoppingItem(@CookieValue("jwt") jwt: String?, @PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody shoppingitem: CreateShoppingItemDTO): ResponseEntity<*>
    {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListById(shoppinglistID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(shoppingItemService.save(shoppingitem, shoppingList).id)
    }

    @RequestMapping( value = ["/{shoppingitemID}"], method = [RequestMethod.DELETE])
    fun deleteShoppingItem(@CookieValue("jwt") jwt: String?, @PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<Unit>
    {
        if(jwt == null){
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
        val si: ShoppingItem = shoppingItemRepository.findShoppingItemById(shoppingitemID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(shoppingItemService.delete(si))
    }

    @RequestMapping(value = ["/{shoppingitemID}/done"], method = [RequestMethod.PUT])
    fun doneShoppingItem(@CookieValue("jwt") jwt: String?, @PathVariable("shoppinglistID") shoppinglistID: Int, @PathVariable("shoppingitemID") shoppingitemID: Int): ResponseEntity<*>
    {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val shoppingItem: ShoppingItem = shoppingItemRepository.findShoppingItemById(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(!shoppingItem.done){
            return ResponseEntity.ok(shoppingItemService.done(shoppingItem))
        }
        return ResponseEntity.badRequest().body(HttpStatus.NOT_MODIFIED)
    }

    @RequestMapping(value = ["/{shoppingitemID}/undone"], method = [RequestMethod.PUT])
    fun undoneShoppingItem(@CookieValue("jwt") jwt: String?, @PathVariable("shoppinglistID") shoppinglistID: Int, @PathVariable("shoppingitemID") shoppingitemID: Int): ResponseEntity<*>
    {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val shoppingItem: ShoppingItem = shoppingItemRepository.findShoppingItemById(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(shoppingItem.done){
            return ResponseEntity.ok(shoppingItemService.undone(shoppingItem))
        }
        return ResponseEntity.badRequest().body(HttpStatus.NOT_MODIFIED)
    }

    @RequestMapping(value = ["/{shoppingitemID}"], method = [RequestMethod.PUT])
    fun editShoppingItem(@CookieValue("jwt") jwt: String?, @PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int,
                         @Valid @RequestBody shoppingitem: ShoppingItem): ResponseEntity<*>
    {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        if (shoppingitemID != shoppingitem.id) {
            return ResponseEntity.badRequest().body("ShoppingItemID not match with the shoppingItemE's id")
        }
        val si = shoppingItemRepository.findShoppingItemById(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(shoppingItemService.edit(shoppingitem, si))
    }

    @RequestMapping(value = ["/all"], method = [RequestMethod.GET])
    fun getAllShoppingItem(@CookieValue("jwt") jwt: String?, @PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*>
    {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val sList: ShoppingList = shoppingListRepository.findShoppingListById(shoppinglistID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(shoppingItemService.byShoppingList(sList))
    }

    @RequestMapping(value = ["/{shoppingitemID}"], method = [RequestMethod.GET])
    fun getShoppingItem(@CookieValue("jwt") jwt: String?, @PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*>
    {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val sItem: ShoppingItem = shoppingItemRepository.findShoppingItemById(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val sItemMapper = Mappers.getMapper(ShoppingItemMapper::class.java)
        val sItemDto = sItemMapper.convertToDto(sItem)
        return ResponseEntity.ok(sItemDto)
    }
}