package hu.bme.aut.familyappbackend.dto

import java.util.*

data class GetFamilyDTO(
    val id: Int,
    val lastModTime: Date? = null,
    var userIDs: List<Int>? = null,
    var shoppingListIDs: List<Int>? = null,
    var inviteIDs: List<Int>? = null
)
