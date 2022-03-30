package hu.bme.aut.familyappbackend.inline

import com.fasterxml.jackson.annotation.JsonProperty

data class Login(
    @field:JsonProperty("password", required = true)
    val password: String,

    @field:JsonProperty("email", required = true)
    val email: String
)
