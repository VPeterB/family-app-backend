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
 * @param userID 
 * @param familyID 
 */
data class Invite(

    @field:JsonProperty("ID", required = true)
    val ID: java.util.UUID,

    @field:JsonProperty("userID", required = true)
    val userID: java.util.UUID,

    @field:JsonProperty("familyID", required = true)
    val familyID: java.util.UUID
) {

}

