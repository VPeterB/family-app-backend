package hu.bme.aut.familyappbackend.dto

import java.util.*

data class GetInviteDTO(
    val id: Int,
    val lastModTime: Date? = null,
    var familyID: Int? = null,
    var userID: Int? = null
)
