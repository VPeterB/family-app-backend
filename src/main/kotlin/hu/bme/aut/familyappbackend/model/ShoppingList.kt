package hu.bme.aut.familyappbackend.model

import java.sql.Timestamp
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
    var lastModTime: Timestamp? = null,

    @ManyToOne
    @JoinColumn(name = "family")
    var family: Family? = null,

    @ManyToMany(mappedBy = "shoppingLists")
    var users: List<User>? = null,

    @OneToMany(mappedBy = "shoppingList")
    var shoppingItems: List<ShoppingItem>? = null
)
