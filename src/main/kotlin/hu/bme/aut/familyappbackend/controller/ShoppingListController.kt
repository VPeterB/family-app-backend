package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateShoppingListDTO
import hu.bme.aut.familyappbackend.mapper.FamilyMapper
import hu.bme.aut.familyappbackend.mapper.ShoppingListMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/shoppinglist")
class ShoppingListController (private val shoppingListRepository: ShoppingListRepository, private val familyRepository: FamilyRepository) {
    @RequestMapping(value = ["/{shoppinglistID}/adduser"], method = [RequestMethod.PUT])
    fun addUserToShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody userID: Int): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST])
    fun createShoppingList(@Valid @RequestBody shoppinglistcreate: CreateShoppingListDTO): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.DELETE])
    fun deleteShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.PUT])
    fun editShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody shoppinglist: ShoppingList): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.GET])
    fun getShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*> {
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.badRequest().body("Shopping list with this ID not found.")
        val shoppingListMapper = Mappers.getMapper(ShoppingListMapper::class.java)
        val shoppingListDto = shoppingListMapper.convertToDto(shoppingList)
        return ResponseEntity.ok(shoppingListDto)
    }

    @RequestMapping(value = ["/byfamily/{familyID}"], method = [RequestMethod.GET])
    fun getShoppingListsByFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body("Family with this ID not found.")
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        val familyDto = familyMapper.convertToDto(family)
        val shoppingListIDs = familyDto.shoppingListIDs
        return ResponseEntity.ok(shoppingListIDs)
    }

    @RequestMapping(value = ["/{shoppinglistID}/removeuser"], method = [RequestMethod.PUT])
    fun removeUserFromShoppingList(
        @PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody userID: Int): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}