package hu.bme.aut.familyappbackend.dto

import java.sql.Timestamp

data class GetFamilyDTO(
    val id: Int,
    val lastModTime: Timestamp? = null,
    var userIDs: List<Int>? = null,
    var shoppingListIDs: List<Int>? = null,
    var eventIDs: List<Int>? = null,
    var inviteIDs: List<Int>? = null
)
