package hu.bme.aut.familyappbackend.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateInviteDTO(
    @field:JsonProperty("email", required = true)
    val email: String,

    @field:JsonProperty("familyID", required = true)
    val familyID: Int
)
