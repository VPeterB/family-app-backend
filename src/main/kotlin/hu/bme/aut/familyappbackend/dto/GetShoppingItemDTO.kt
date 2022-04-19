package hu.bme.aut.familyappbackend.dto

data class GetShoppingItemDTO(
    val ID: Int,
    val name: String,
    val done: Boolean,
    val shoppingListID: Int
)
