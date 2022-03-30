package hu.bme.aut.familyappbackend.inline

import com.fasterxml.jackson.annotation.JsonProperty

data class CSL(
    @field:JsonProperty("familyID", required = true)
    val familyID: Int,

    @field:JsonProperty("name", required = true)
    val name: String
)
