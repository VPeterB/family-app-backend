package hu.bme.aut.familyappbackend.dto

data class FamilyDTO(
    val ID: Int,
    val userIDs: List<Int>? = null,
    val shoppingListIDs: List<Int>? = null,
    val inviteIDs: List<Int>? = null
)
