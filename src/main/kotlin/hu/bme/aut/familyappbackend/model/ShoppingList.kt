package hu.bme.aut.familyappbackend.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.aut.familyappbackend.model.InlineResponse200
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

/**
 * 
 * @param ID 
 * @param name 
 * @param familyID 
 * @param userIDs 
 * @param itemIDs 
 */
data class ShoppingList(

    @field:JsonProperty("ID", required = true)
    val ID: java.util.UUID,

    @field:JsonProperty("name", required = true)
    val name: kotlin.String,

    @field:JsonProperty("familyID", required = true)
    val familyID: java.util.UUID,

    @field:Valid
    @field:JsonProperty("userIDs")
    val userIDs: kotlin.collections.List<InlineResponse200>? = null,

    @field:Valid
    @field:JsonProperty("itemIDs")
    val itemIDs: kotlin.collections.List<InlineResponse200>? = null
) {

}

