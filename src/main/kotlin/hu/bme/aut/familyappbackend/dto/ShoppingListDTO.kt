package hu.bme.aut.familyappbackend.dto

data class ShoppingListDTO(
    val ID: Int,
    val name: String,
    val familyID: Int,
    var userIDs: List<Int>? = null,
    val shoppingItemIDs: List<Int>? = null
)
