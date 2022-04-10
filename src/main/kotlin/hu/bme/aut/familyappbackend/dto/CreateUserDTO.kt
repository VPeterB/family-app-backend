package hu.bme.aut.familyappbackend.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateUserDTO(
    @field:JsonProperty("password", required = true)
    val password: String,

    @field:JsonProperty("email", required = true)
    val email: String
)
