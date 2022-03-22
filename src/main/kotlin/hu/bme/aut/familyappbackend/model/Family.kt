package hu.bme.aut.familyappbackend.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.aut.familyappbackend.model.InlineResponse200
import javax.persistence.*
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
@Entity
@Table(name = "familys")
data class Family(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("ID", required = true)
    val ID: Int,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    @field:Valid
    @field:JsonProperty("users")
    val users: List<User>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    @field:Valid
    @field:JsonProperty("shoppinglists")
    val shoppingLists: List<ShoppingList>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    @field:Valid
    @field:JsonProperty("invites")
    val invites: List<Invite>? = null

) {

}

