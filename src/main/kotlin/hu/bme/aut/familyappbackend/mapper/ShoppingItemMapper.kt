package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetShoppingItemDTO
import hu.bme.aut.familyappbackend.model.ShoppingItem
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ShoppingItemMapper {
    fun convertToDto(shoppingItem: ShoppingItem) : GetShoppingItemDTO {
        return GetShoppingItemDTO(shoppingItem.ID, shoppingItem.name, shoppingItem.done, shoppingItem.shoppingList.ID )
    }
}