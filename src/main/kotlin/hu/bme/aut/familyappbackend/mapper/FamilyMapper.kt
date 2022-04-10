package hu.bme.aut.familyappbackend.mapper

import hu.bme.aut.familyappbackend.dto.GetFamilyDTO
import hu.bme.aut.familyappbackend.model.Family
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface FamilyMapper {
    fun convertToDto(family: Family) : GetFamilyDTO {
        val shoppingIDS: MutableList<Int> = mutableListOf()
        val userIDS: MutableList<Int> = mutableListOf()
        val inviteIDS: MutableList<Int> = mutableListOf()
        for (shoppingList in family.shoppingLists!!){
            shoppingIDS.add(shoppingList.ID)
        }
        for (invite in family.invites!!){
            inviteIDS.add(invite.ID)
        }
        for (user in family.users!!){
            userIDS.add(user.ID)
        }
        return GetFamilyDTO(family.ID, userIDS, shoppingIDS, inviteIDS)
    }
}