package hu.bme.aut.familyappbackend.dto

import java.sql.Timestamp

data class GetInviteDTO(
    val id: Int,
    val lastModTime: Timestamp? = null,
    var familyID: Int? = null,
    var userID: Int? = null
)
