package hu.bme.aut.familyappbackend.dto

import java.sql.Timestamp

data class GetShoppingItemDTO(
    val id: Int,
    val name: String,
    val done: Boolean,
    val lastModTime: Timestamp? = null,
    var shoppingListID: Int?
)
