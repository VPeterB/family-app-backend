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
 * @param picture 
 * @param userIDs 
 * @param shoppinglistIDs 
 * @param inviteIDs 
 */
data class Family(

    @field:JsonProperty("ID", required = true) val ID: java.util.UUID,
    @get:Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$")
    @field:JsonProperty("picture") val picture: kotlin.ByteArray? = null,

    @field:Valid
    @field:JsonProperty("userIDs") val userIDs: kotlin.collections.List<InlineResponse200>? = null,

    @field:Valid
    @field:JsonProperty("shoppinglistIDs") val shoppinglistIDs: kotlin.collections.List<InlineResponse200>? = null,

    @field:Valid
    @field:JsonProperty("inviteIDs") val inviteIDs: kotlin.collections.List<InlineResponse200>? = null
) {

}

