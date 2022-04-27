package hu.bme.aut.familyappbackend.dto

data class GetShoppingItemDTO(
    val id: Int,
    val name: String,
    val done: Boolean,
    var shoppingListID: Int?
)
