package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetShoppingListDTO
import hu.bme.aut.familyappbackend.dto.GetUserDTO
import hu.bme.aut.familyappbackend.model.ShoppingList
import hu.bme.aut.familyappbackend.model.User
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class ShoppingListMapper {
    abstract fun convertToDto(shoppingList: ShoppingList) : GetShoppingListDTO

    @AfterMapping
    fun getId(@MappingTarget target: GetShoppingListDTO, source: ShoppingList){
        val shoppingItemIDS: MutableList<Int> = mutableListOf()
        val userIDS: MutableList<Int> = mutableListOf()
        for (shoppingItem in source.shoppingItems!!){
            shoppingItemIDS.add(shoppingItem.id)
        }
        for (user in source.users!!){
            userIDS.add(user.id)
        }
        target.shoppingItemIDs = shoppingItemIDS
        target.userIDs = userIDS
        target.familyID = source.family?.id
    }
}