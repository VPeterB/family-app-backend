package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetUserDTO
import hu.bme.aut.familyappbackend.model.User
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class UserMapper {

    abstract  fun convertToDto(user: User) : GetUserDTO

    @AfterMapping
    fun getId(@MappingTarget target:GetUserDTO, source:User){
        target.familyID = source.family?.id
        target.inviteID = source.invite?.id
        val shoppingIDS: MutableList<Int> = mutableListOf()
        for (shoppingList in source.shoppingLists!!){
            shoppingIDS.add(shoppingList.id)
        }
        val eventIDS: MutableList<Int> = mutableListOf()
        for (event in source.events!!){
            eventIDS.add(event.id)
        }
        target.shoppingListIDs = shoppingIDS
    }
}