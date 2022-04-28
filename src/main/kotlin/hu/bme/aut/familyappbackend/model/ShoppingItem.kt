package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "shoppingitems")
data class ShoppingItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("id", required = true)
    val id: Int,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "done")
    var done: Boolean = false,

    @Column(name = "lastModTime")
    var lastModTime: Date? = null,

    @ManyToOne
    @JoinColumn(name = "shoppinglist")
    var shoppingList: ShoppingList?  = null
)
