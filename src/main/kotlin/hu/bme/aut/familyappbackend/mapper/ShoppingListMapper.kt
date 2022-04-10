package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetShoppingListDTO
import hu.bme.aut.familyappbackend.model.ShoppingList
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ShoppingListMapper {
    fun convertToDto(shoppingList: ShoppingList) : GetShoppingListDTO {
        val shoppingItemIDS: MutableList<Int> = mutableListOf()
        val userIDS: MutableList<Int> = mutableListOf()
        for (shoppingItem in shoppingList.shoppingItems!!){
            shoppingItemIDS.add(shoppingItem.ID)
        }
        for (user in shoppingList.users!!){
            userIDS.add(user.ID)
        }
        return GetShoppingListDTO(shoppingList.ID, shoppingList.name, shoppingList.family.ID, userIDS, shoppingItemIDS)
    }
}