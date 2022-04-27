package hu.bme.aut.familyappbackend.dto

data class GetInviteDTO(
    val id: Int,
    var familyID: Int? = null,
    var userID: Int? = null
)
