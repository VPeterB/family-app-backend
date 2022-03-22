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
 * @param familyID
 * @param name
 */
data class InlineObject3(

    @field:JsonProperty("familyID")
    val familyID: kotlin.String? = null,

    @field:JsonProperty("name")
    val name: kotlin.String? = null
) {

}

