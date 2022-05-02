package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "shoppinglists")
data class ShoppingList(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    val id: Int,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "lastModTime")
    var lastModTime: Date? = null,

    @ManyToOne
    @JoinColumn(name = "family")
    var family: Family? = null,

    @ManyToMany(mappedBy = "shoppingLists")
    var users: List<User>? = null,

    @OneToMany(mappedBy = "shoppingList")
    var shoppingItems: List<ShoppingItem>? = null
)
