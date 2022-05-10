package hu.bme.aut.familyappbackend.dto

import java.sql.Timestamp

data class GetShoppingListDTO(
    val id: Int,
    val name: String,
    val lastModTime: Timestamp? = null,
    var familyID: Int?,
    var userIDs: List<Int>? = null,
    var shoppingItemIDs: List<Int>? = null
)
