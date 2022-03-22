package hu.bme.aut.familyappbackend.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param shoppinglistID
 * @param done
 */
@Entity
@Table(name = "shoppingitems")
data class ShoppingItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("ID", required = true)
    val ID: Int,

    @Column(name = "name", nullable = false)
    @field:JsonProperty("name", required = true)
    val name: String,

    @Column(name = "done")
    @field:JsonProperty("done")
    val done: Boolean? = null,

    @ManyToOne
    @JoinColumn(name = "shoppinglist")
    @field:JsonProperty("shoppinglist")
    val shoppingList: ShoppingList? = null
) {

}

