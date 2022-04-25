package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateShoppingItemDTO
import hu.bme.aut.familyappbackend.mapper.ShoppingItemMapper
import hu.bme.aut.familyappbackend.mapper.ShoppingListMapper
import hu.bme.aut.familyappbackend.mapper.UserMapper
import hu.bme.aut.familyappbackend.model.ShoppingItem
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.ShoppingItemRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import hu.bme.aut.familyappbackend.service.ShoppingItemService
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/shoppinglist/{shoppinglistID}/shoppingitem")
class ShoppingItemController (private val shoppingItemRepository: ShoppingItemRepository, private val shoppingListRepository: ShoppingListRepository, private val shoppingItemService: ShoppingItemService){
    @RequestMapping(value = ["/add"], method = [RequestMethod.POST])
    fun addShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody shoppingitem: CreateShoppingItemDTO): ResponseEntity<*>
    {
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(shoppingItemService.save(shoppingitem, shoppingList).ID) //id visszaküldése <- //DONE backend visszaadja e az id-t
    }

    @RequestMapping( value = ["/{shoppingitemID}"], method = [RequestMethod.DELETE])
    fun deleteShoppingItem(@PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<Unit>
    {
        val si: ShoppingItem = shoppingItemRepository.findShoppingItemByID(shoppingitemID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(shoppingItemService.delete(si))
    }

    @RequestMapping(value = ["/{shoppingitemID}/done"], method = [RequestMethod.PUT])
    fun doneShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int, @PathVariable("shoppingitemID") shoppingitemID: Int): ResponseEntity<*>
    {
        val shoppingItem: ShoppingItem = shoppingItemRepository.findShoppingItemByID(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(!shoppingItem.done){
            shoppingItem.done = true
            return ResponseEntity.ok(shoppingItemRepository.save(shoppingItem))
        }
        return ResponseEntity.badRequest().body(HttpStatus.NOT_MODIFIED)
    }

    @RequestMapping(value = ["/{shoppingitemID}/undone"], method = [RequestMethod.PUT])
    fun undoneShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int, @PathVariable("shoppingitemID") shoppingitemID: Int): ResponseEntity<*>
    {
        val shoppingItem: ShoppingItem = shoppingItemRepository.findShoppingItemByID(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if(shoppingItem.done){
            shoppingItem.done = false
            return ResponseEntity.ok(shoppingItemRepository.save(shoppingItem))
        }
        return ResponseEntity.badRequest().body(HttpStatus.NOT_MODIFIED)
    }

    @RequestMapping(value = ["/{shoppingitemID}"], method = [RequestMethod.PUT])
    fun editShoppingItem(@PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int,
                         @Valid @RequestBody shoppingitem: ShoppingItem): ResponseEntity<*>
    {
        shoppingItemRepository.findShoppingItemByID(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        if (shoppingitemID != shoppingitem.ID) {
            return ResponseEntity.badRequest().body("ShoppingItemID not match with the shoppingItemE's id")
        }
        return ResponseEntity.ok(shoppingItemRepository.save(shoppingitem))
    }

    @RequestMapping(value = ["/all"], method = [RequestMethod.GET])
    fun getAllShoppingItem(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*>
    {
        val sList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val sListMapper = Mappers.getMapper(ShoppingListMapper::class.java)
        val sListDto = sListMapper.convertToDto(sList)
        return ResponseEntity.ok(sListDto.shoppingItemIDs)  // TODO backend más: kell a dto lista vagy elég így az id lista?
    }

    @RequestMapping(value = ["/{shoppingitemID}"], method = [RequestMethod.GET])
    fun getShoppingItem(@PathVariable("shoppingitemID") shoppingitemID: Int, @PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*>
    {
        val sItem: ShoppingItem = shoppingItemRepository.findShoppingItemByID(shoppingitemID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val sItemMapper = Mappers.getMapper(ShoppingItemMapper::class.java)
        val sItemDto = sItemMapper.convertToDto(sItem)
        return ResponseEntity.ok(sItemDto)
    }
}