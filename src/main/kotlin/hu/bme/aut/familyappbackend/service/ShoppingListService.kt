package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.GetShoppingListDTO
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
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class ShoppingListService (private val shoppingListRepository: ShoppingListRepository, private val userRepository: UserRepository, private val familyRepository: FamilyRepository, private val shoppingItemService: ShoppingItemService, private val userService: UserService){
    fun addUser(shoppingList: ShoppingList, user: User): ShoppingList{
        var sUsers: MutableList<User> = mutableListOf()
        if(shoppingList.users != null){
            sUsers= shoppingList.users as MutableList<User>
        }
        sUsers.add(user)
        shoppingList.users = sUsers
        shoppingList.lastModTime = Timestamp(System.currentTimeMillis())
        val sl = shoppingListRepository.save(shoppingList)
        var uShoppingLists: MutableList<ShoppingList> = mutableListOf()
        if(user.shoppingLists != null){
            uShoppingLists = user.shoppingLists as MutableList<ShoppingList>
        }
        uShoppingLists.add(sl)
        user.shoppingLists = uShoppingLists
        user.lastModTime = Timestamp(System.currentTimeMillis())
        userRepository.save(user)
        return sl
    }

    fun delete(shoppingList: ShoppingList){
        if(shoppingList.users != null){
            val users: MutableList<User> = shoppingList.users as MutableList<User>
            for(user in users){
                val uLists = user.shoppingLists as MutableList<ShoppingList>
                if(uLists.contains(shoppingList))
                    uLists.remove(shoppingList)
                user.lastModTime = Timestamp(System.currentTimeMillis())
                userRepository.save(user)
            }
        }
        val family = shoppingList.family
        if(family?.shoppingLists != null){
            val fLists = family.shoppingLists as MutableList<ShoppingList>
            if(fLists.contains(shoppingList))
                fLists.remove(shoppingList)
            family.lastModTime = Timestamp(System.currentTimeMillis())
            familyRepository.save(family)
        }
        val slItems = shoppingList.shoppingItems
        if (slItems != null) {
            shoppingList.shoppingItems = null
            for(item in slItems){
                shoppingItemService.delete(item)
            }
        }
        shoppingListRepository.save(shoppingList)
        shoppingListRepository.delete(shoppingList)
    }

    fun removeUser(shoppingListID: Int, userID: Int): ResponseEntity<Unit>{
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListById(shoppingListID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val user: User = userRepository.findUserById(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        if(user.shoppingLists == null)
            return ResponseEntity(HttpStatus.NOT_MODIFIED)
        if(shoppingList.users == null)
            return ResponseEntity(HttpStatus.NOT_MODIFIED)
        val users: MutableList<User> = shoppingList.users as MutableList<User>
        if(!users.contains(user))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        users.remove(user)
        shoppingList.users = users
        shoppingList.lastModTime = Timestamp(System.currentTimeMillis())
        shoppingListRepository.save(shoppingList)
        val uShoppingLists: MutableList<ShoppingList> = user.shoppingLists as MutableList<ShoppingList>
        if(!uShoppingLists.contains(shoppingList))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        uShoppingLists.remove(shoppingList)
        user.shoppingLists = uShoppingLists
        user.lastModTime = Timestamp(System.currentTimeMillis())
        userRepository.save(user)
        return ResponseEntity(HttpStatus.OK)
    }

    fun toDtoList(sls: MutableList<ShoppingList>): MutableList<GetShoppingListDTO>{
        val rSL = mutableListOf<GetShoppingListDTO>()
        val shoppingListMapper = Mappers.getMapper(ShoppingListMapper::class.java)
        for(sl in sls){
            val shoppingListDTO = shoppingListMapper.convertToDto(sl)
            rSL.add(shoppingListDTO)
        }
        return rSL
    }

    fun byFamily(family: Family): MutableList<GetShoppingListDTO>? {
        if(family.shoppingLists != null){
            val shoppingLists = family.shoppingLists as MutableList<ShoppingList>
            return toDtoList(shoppingLists)
        }
        return null
    }

    fun byUser(user: User): MutableList<GetShoppingListDTO>? {
        if (user.shoppingLists != null){
            val shoppingLists = user.shoppingLists as MutableList<ShoppingList>
            return toDtoList(shoppingLists)
        }
        return null
    }

    fun edit(shoppingList: ShoppingList, sl: ShoppingList): GetShoppingListDTO{
        shoppingList.users = sl.users
        shoppingList.family = sl.family
        shoppingList.shoppingItems = sl.shoppingItems
        shoppingList.lastModTime = Timestamp(System.currentTimeMillis())
        val sL = shoppingListRepository.save(shoppingList)
        val shoppingListMapper = Mappers.getMapper(ShoppingListMapper::class.java)
        return shoppingListMapper.convertToDto(sL)
    }

    fun checkShoppingListMember(shoppingList: ShoppingList, jwt: String): Boolean{
        val user = userService.getUserByJWT(jwt)?: return false
        val slUsers = shoppingList.users
        if (slUsers != null) {
            if(slUsers.contains(user)){
                return true
            }
        }
        return false
    }
}