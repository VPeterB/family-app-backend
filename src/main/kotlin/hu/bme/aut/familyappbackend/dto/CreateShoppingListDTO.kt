package hu.bme.aut.familyappbackend.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateShoppingListDTO(
    @field:JsonProperty("familyID", required = true)
    val familyID: Int,

    @field:JsonProperty("name", required = true)
    val name: String
)
