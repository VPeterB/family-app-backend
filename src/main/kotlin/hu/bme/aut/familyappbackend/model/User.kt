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
 * @param email 
 * @param password 
 * @param phonenumber 
 * @param firstname 
 * @param lastname 
 * @param username 
 * @param category 
 * @param profilepicture 
 * @param familyID 
 * @param inviteIDs 
 * @param shoppinglistIDs 
 */
data class User(

    @field:JsonProperty("ID", required = true)
    val ID: java.util.UUID,

    @field:JsonProperty("email", required = true)
    val email: kotlin.String,

    @field:JsonProperty("password", required = true)
    val password: kotlin.String,

    @field:JsonProperty("phonenumber")
    val phonenumber: kotlin.String? = null,

    @field:JsonProperty("firstname")
    val firstname: kotlin.String? = null,

    @field:JsonProperty("lastname")
    val lastname: kotlin.String? = null,

    @field:JsonProperty("username")
    val username: kotlin.String? = null,

    @field:JsonProperty("category")
    val category: kotlin.String? = null,
    //@get:Pattern(regexp="^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$")
    @field:JsonProperty("profilepicture")
    val profilepicture: kotlin.ByteArray? = null,

    @field:JsonProperty("familyID")
    val familyID: java.util.UUID? = null,

    @field:Valid
    @field:JsonProperty("inviteIDs")
    val inviteIDs: kotlin.collections.List<InlineResponse200>? = null,

    @field:Valid
    @field:JsonProperty("shoppinglistIDs")
    val shoppinglistIDs: kotlin.collections.List<InlineResponse200>? = null
) {

}

