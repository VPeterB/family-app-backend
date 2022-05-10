package hu.bme.aut.familyappbackend.dto

import java.sql.Timestamp

data class GetEventDTO(
    val id: Int,
    val name: String,
    val location: String? = null,
    val description: String? = null,
    val end: Timestamp,
    val start: Timestamp,
    val lastModTime: Timestamp? = null,
    var familyID: Int?,
    var userIDs: List<Int>? = null
)
