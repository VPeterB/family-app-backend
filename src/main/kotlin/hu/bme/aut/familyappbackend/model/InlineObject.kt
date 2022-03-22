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
 * @param password
 * @param eamil
 */
data class InlineObject(

    @field:JsonProperty("password", required = true)
    val password: kotlin.String,

    @field:JsonProperty("eamil")
    val eamil: kotlin.String? = null
) {

}

