package hu.bme.aut.familyappbackend.dto

import hu.bme.aut.familyappbackend.model.Family
import hu.bme.aut.familyappbackend.model.User

data class GetInviteDTO(
    val ID: Int,
    var familyID: Int? = null,
    var userID: Int? = null
)
