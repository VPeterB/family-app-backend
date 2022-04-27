package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "familys")
data class Family (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("ID", required = true)
    val id: Int,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    var users: List<User>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    var shoppingLists: List<ShoppingList>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    var invites: List<Invite>? = null
)
{
}