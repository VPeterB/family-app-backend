package hu.bme.aut.familyappbackend.dto

data class GetShoppingListDTO(
    val id: Int,
    val name: String,
    var familyID: Int?,
    var userIDs: List<Int>? = null,
    var shoppingItemIDs: List<Int>? = null
)
