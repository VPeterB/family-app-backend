package hu.bme.aut.familyappbackend.controller

import hu.bme.aut.familyappbackend.dto.CreateShoppingListDTO
import hu.bme.aut.familyappbackend.mapper.ShoppingListMapper
import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import hu.bme.aut.familyappbackend.repository.FamilyRepository
import hu.bme.aut.familyappbackend.repository.ShoppingListRepository
import hu.bme.aut.familyappbackend.repository.UserRepository
import hu.bme.aut.familyappbackend.service.UserService
import hu.bme.aut.familyappbackend.service.ShoppingListService
import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/shoppinglist")
class ShoppingListController (private val shoppingListRepository: ShoppingListRepository, private val familyRepository: FamilyRepository, private val userRepository: UserRepository, private val userService: UserService, private val sLService: ShoppingListService) {
    @RequestMapping(value = ["/{shoppinglistID}/adduser"], method = [RequestMethod.PUT])
    fun addUserToShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody userID: Int): ResponseEntity<Unit> {
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        sLService.addUser(shoppingList, user)
        return ResponseEntity(HttpStatus.OK)
    }

    @RequestMapping(value = ["/create"], method = [RequestMethod.POST])
    fun createShoppingList(@CookieValue("jwt") jwt: String?, @Valid @RequestBody shoppinglistcreate: CreateShoppingListDTO): ResponseEntity<*> {
        if(jwt == null){
            return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        }
        val user = userService.getUserByJWT(jwt)?: return ResponseEntity.status(401).body(HttpStatus.UNAUTHORIZED)
        val newSL = ShoppingList(0, shoppinglistcreate.name)
        if(shoppinglistcreate.familyID != null){
            val family: Family = familyRepository.findFamilyByID(shoppinglistcreate.familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
            newSL.family = family
        }
        val sl = sLService.addUser(newSL, user)
        return ResponseEntity.ok(sl.ID)
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.DELETE])
    fun deleteShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<Unit> {
        val sl: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(sLService.delete(sl))
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.PUT])
    fun editShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody shoppinglist: ShoppingList): ResponseEntity<*> {
        if (shoppinglistID != shoppinglist.ID) {
            return ResponseEntity.badRequest().body("ShoppingListID not match with the shoppingListE's id")
        }
        val sl = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(sLService.edit(shoppinglist, sl))
    }

    @RequestMapping(value = ["/{shoppinglistID}"], method = [RequestMethod.GET])
    fun getShoppingList(@PathVariable("shoppinglistID") shoppinglistID: Int): ResponseEntity<*> {
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppinglistID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        val shoppingListMapper = Mappers.getMapper(ShoppingListMapper::class.java)
        val shoppingListDto = shoppingListMapper.convertToDto(shoppingList)
        return ResponseEntity.ok(shoppingListDto)
    }

    @RequestMapping(value = ["/byfamily/{familyID}"], method = [RequestMethod.GET])
    fun getShoppingListsByFamily(@PathVariable("familyID") familyID: Int): ResponseEntity<*> {
        val family: Family = familyRepository.findFamilyByID(familyID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(sLService.byFamily(family))
    }

    @RequestMapping(value = ["/byuser/{userID}"], method = [RequestMethod.GET])
    fun getShoppingListsByUser(@PathVariable("userID") userID: Int): ResponseEntity<*> {
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity.badRequest().body(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(sLService.byUser(user))
    }

    @RequestMapping(value = ["/{shoppinglistID}/removeuser"], method = [RequestMethod.PUT])
    fun removeUserFromShoppingList(
        @PathVariable("shoppinglistID") shoppinglistID: Int, @Valid @RequestBody userID: Int): ResponseEntity<Unit> {
        return sLService.removeUser(shoppinglistID, userID)
    }
}