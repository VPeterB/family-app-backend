package hu.bme.aut.familyappbackend.inline

import com.fasterxml.jackson.annotation.JsonProperty

data class Inv(
    @field:JsonProperty("email", required = true)
    val email: String,

    @field:JsonProperty("familyID", required = true)
    val familyID: Int
)
