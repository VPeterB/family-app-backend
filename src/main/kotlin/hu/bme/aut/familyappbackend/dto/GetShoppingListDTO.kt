package hu.bme.aut.familyappbackend.dto

data class GetShoppingListDTO(
    val ID: Int,
    val name: String,
    var familyID: Int?,
    var userIDs: List<Int>? = null,
    var shoppingItemIDs: List<Int>? = null
)
