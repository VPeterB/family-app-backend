package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetShoppingItemDTO
import hu.bme.aut.familyappbackend.model.ShoppingItem
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class ShoppingItemMapper {
    abstract fun convertToDto(shoppingItem: ShoppingItem) : GetShoppingItemDTO

    @AfterMapping
    fun getId(@MappingTarget target: GetShoppingItemDTO, source: ShoppingItem){
        target.shoppingListID = source.shoppingList?.id
    }
}