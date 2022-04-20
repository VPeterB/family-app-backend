package hu.bme.aut.familyappbackend.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateShoppingListDTO(
    @field:JsonProperty("familyID")
    val familyID: Int?,

    @field:JsonProperty("name", required = true)
    val name: String
)
