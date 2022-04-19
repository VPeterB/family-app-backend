package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateShoppingListDTO
import hu.bme.aut.familyappbackend.mapper.FamilyMapper
import hu.bme.aut.familyappbackend.mapper.ShoppingListMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/shoppinglist")
class ShoppingListController (private val shoppingListRepository: ShoppingListRepository, private val familyRepository: FamilyRepository, private val userRepository: UserRepository) {
    @RequestMapping(value = ["/{shoppinglistID}/adduser"], method = [RequestMethod.PUT])
    fun addUserToShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody userID: Int): ResponseEntity<*> {
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val users: MutableList<User> = shoppingList.users as MutableList<User>
        users.add(user)
        shoppingList.users = users
        return ResponseEntity.ok(shoppingListRepository.save(shoppingList))
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST])
    fun createShoppingList(@Valid @RequestBody shoppinglistcreate: CreateShoppingListDTO): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyByID(shoppinglistcreate.familyID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val newSL = ShoppingList(0, shoppinglistcreate.name, family)
        return ResponseEntity.ok(shoppingListRepository.save(newSL))
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.DELETE])
    fun deleteShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*> {
        val sl: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(shoppingListRepository.delete(sl))
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.PUT])
    fun editShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody shoppinglist: ShoppingList): ResponseEntity<*> {
        shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        if (shoppinglistID != shoppinglist.ID) {
            return ResponseEntity.badRequest().body("ShoppingListID not match with the shoppingListE's id")
        }
        return ResponseEntity.ok(shoppingListRepository.save(shoppinglist))
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.GET])
    fun getShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*> {
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val shoppingListMapper = Mappers.getMapper(ShoppingListMapper::class.java)
        val shoppingListDto = shoppingListMapper.convertToDto(shoppingList)
        return ResponseEntity.ok(shoppingListDto)
    }

    @RequestMapping(value = ["/byfamily/{familyID}"], method = [RequestMethod.GET])
    fun getShoppingListsByFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val familyMapper = Mappers.getMapper(FamilyMapper::class.java)
        val familyDto = familyMapper.convertToDto(family)
        val shoppingListIDs = familyDto.shoppingListIDs
        return ResponseEntity.ok(shoppingListIDs)
    }

    @RequestMapping(value = ["/{shoppinglistID}/removeuser"], method = [RequestMethod.PUT])
    fun removeUserFromShoppingList(
        @PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody userID: Int): ResponseEntity<*> {
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        val users: MutableList<User> = shoppingList.users as MutableList<User>
        if(!users.contains(user))
            return ResponseEntity.ok(HttpStatus.NOT_FOUND)
        users.remove(user)
        shoppingList.users = users
        return ResponseEntity.ok(shoppingListRepository.save(shoppingList))
    }
}