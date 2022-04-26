package hu.bme.aut.familyappbackend.service

import hu.bme.aut.familyappbackend.dto.GetShoppingListDTO
import hu.bme.aut.familyappbackend.mapper.ShoppingListMapper
import hu.bme.aut.familyappbackend.mapper.UserMapper
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

@Service
class ShoppingListService (private val shoppingListRepository: ShoppingListRepository, private val userRepository: UserRepository, private val familyRepository: FamilyRepository){
    fun addUser(shoppingList: ShoppingList, user: User): ShoppingList{
        val sUsers: MutableList<User> = shoppingList.users as MutableList<User>
        sUsers.add(user)
        shoppingList.users = sUsers
        val sl = shoppingListRepository.save(shoppingList)
        val uShoppingLists: MutableList<ShoppingList> = user.shoppingLists as MutableList<ShoppingList>
        uShoppingLists.add(sl)
        user.shoppingLists = uShoppingLists
        userRepository.save(user)
        return sl
    }

    fun delete(shoppingList: ShoppingList){
        val users: MutableList<User> = shoppingList.users as MutableList<User>
        for(user in users){
            val uLists = user.shoppingLists as MutableList<ShoppingList>
            if(uLists.contains(shoppingList))
                uLists.remove(shoppingList)
            userRepository.save(user)
        }
        val family = shoppingList.family
        val fLists = family?.shoppingLists as MutableList<ShoppingList>
        if(fLists.contains(shoppingList))
            fLists.remove(shoppingList)
        familyRepository.save(family)
    }

    fun removeUser(shoppingListID: Int, userID: Int): ResponseEntity<Unit>{
        val shoppingList: ShoppingList = shoppingListRepository.findShoppingListByID(shoppingListID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val user: User = userRepository.findUserByID(userID)?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val users: MutableList<User> = shoppingList.users as MutableList<User>
        if(!users.contains(user))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        users.remove(user)
        shoppingList.users = users
        shoppingListRepository.save(shoppingList)
        val uShoppingLists: MutableList<ShoppingList> = user.shoppingLists as MutableList<ShoppingList>
        if(!uShoppingLists.contains(shoppingList))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        uShoppingLists.remove(shoppingList)
        user.shoppingLists = uShoppingLists
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

    fun byFamily(family: Family): MutableList<GetShoppingListDTO> {
        val shoppingLists = family.shoppingLists as MutableList<ShoppingList>
        return toDtoList(shoppingLists)
    }

    fun byUser(user: User): MutableList<GetShoppingListDTO>{
        val shoppingLists = user.shoppingLists as MutableList<ShoppingList>
        return toDtoList(shoppingLists)
    }

    fun edit(shoppingList: ShoppingList, sl: ShoppingList): ShoppingList{
        shoppingList.users = sl.users
        shoppingList.family = sl.family
        shoppingList.shoppingItems = sl.shoppingItems
        return shoppingListRepository.save(shoppingList)
    }
}