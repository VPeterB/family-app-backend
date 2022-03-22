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
 * @param name
 * @param familyID
 * @param userIDs
 * @param itemIDs
 */
@Entity
@Table(name = "shoppinglists")
data class ShoppingList(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("ID", required = true)
    val ID: Int,

    @Column(name = "name", nullable = false)
    @field:JsonProperty("name", required = true)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "family")
    @field:JsonProperty("family")
    val family: Family? = null,

    @ManyToMany(mappedBy = "shoppingLists")
    var users: List<User>? = null,

    @OneToMany(mappedBy = "shoppingList", cascade = [CascadeType.ALL])
    @field:Valid
    @field:JsonProperty("shoppinglists")
    val shoppingItems: List<ShoppingItem>? = null

) {

}

