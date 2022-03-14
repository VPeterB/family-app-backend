package hu.bme.aut.familyappbackend.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param shoppinglistID 
 * @param done 
 */
data class ShoppingItem(

    @field:JsonProperty("ID", required = true) val ID: java.util.UUID,

    @field:JsonProperty("name", required = true) val name: kotlin.String,

    @field:JsonProperty("shoppinglistID", required = true) val shoppinglistID: java.util.UUID,

    @field:JsonProperty("done") val done: kotlin.Boolean? = null
) {

}

