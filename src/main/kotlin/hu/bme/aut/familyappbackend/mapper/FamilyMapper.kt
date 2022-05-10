package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetFamilyDTO
import hu.bme.aut.familyappbackend.model.Family
import org.mapstruct.AfterMapping
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget

@Mapper(componentModel = "spring")
abstract class FamilyMapper {
    abstract fun convertToDto(family: Family) : GetFamilyDTO

    @AfterMapping
    fun getID(@MappingTarget target: GetFamilyDTO, source: Family){
        val shoppingIDS: MutableList<Int> = mutableListOf()
        val userIDS: MutableList<Int> = mutableListOf()
        val inviteIDS: MutableList<Int> = mutableListOf()
        val eventIDS: MutableList<Int> = mutableListOf()
        for (shoppingList in source.shoppingLists!!){
            shoppingIDS.add(shoppingList.id)
        }
        for (invite in source.invites!!){
            inviteIDS.add(invite.id)
        }
        for (user in source.users!!){
            userIDS.add(user.id)
        }
        for (event in source.events!!){
            eventIDS.add(event.id)
        }
        target.userIDs = userIDS
        target.inviteIDs = inviteIDS
        target.shoppingListIDs = shoppingIDS
        target.eventIDs = eventIDS
    }
}