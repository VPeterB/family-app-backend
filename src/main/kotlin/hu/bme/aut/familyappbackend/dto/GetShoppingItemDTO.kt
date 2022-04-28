package hu.bme.aut.familyappbackend.dto

import java.util.*

data class GetShoppingItemDTO(
    val id: Int,
    val name: String,
    val done: Boolean,
    val lastModTime: Date? = null,
    var shoppingListID: Int?
)
