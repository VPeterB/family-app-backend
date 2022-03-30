package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "shoppinglists")
data class ShoppingList(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    val ID: Int,

    @Column(name = "name", nullable = false)
    val name: String,

    @ManyToOne
    @JoinColumn(name = "family")
    val family: Family? = null,

    @ManyToMany(mappedBy = "shoppingLists")
    var users: List<User>? = null,

    @OneToMany(mappedBy = "shoppingList", cascade = [CascadeType.ALL])
    val shoppingItems: List<ShoppingItem>? = null
)
