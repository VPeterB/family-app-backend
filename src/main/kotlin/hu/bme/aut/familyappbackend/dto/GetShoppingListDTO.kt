package hu.bme.aut.familyappbackend.dto

data class GetShoppingListDTO(
    val ID: Int,
    val name: String,
    val familyID: Int,
    var userIDs: List<Int>? = null,
    val shoppingItemIDs: List<Int>? = null
)
