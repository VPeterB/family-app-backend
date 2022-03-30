package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "shoppingitems")
data class ShoppingItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("ID", required = true)
    val ID: Int,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "done")
    val done: Boolean? = null,

    @ManyToOne
    @JoinColumn(name = "shoppinglist")
    val shoppingList: ShoppingList? = null
)
