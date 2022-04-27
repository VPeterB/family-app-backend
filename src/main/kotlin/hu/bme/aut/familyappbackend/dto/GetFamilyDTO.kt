package hu.bme.aut.familyappbackend.dto

data class GetFamilyDTO(
    val id: Int,
    var userIDs: List<Int>? = null,
    var shoppingListIDs: List<Int>? = null,
    var inviteIDs: List<Int>? = null
)
