package hu.bme.aut.familyappbackend.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateShoppingItemDTO(
    @field:JsonProperty("name", required = true)
    val name: String,

    @field:JsonProperty("done")
    val done: Boolean
)
