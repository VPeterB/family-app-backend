package hu.bme.aut.familyappbackend.dto


data class ShoppingItemDTO(
    val ID: Int,
    val name: String,
    val done: Boolean,
    val shoppingList: Int
)
