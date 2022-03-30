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
    val ID: Int,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    val users: List<User>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    val shoppingLists: List<ShoppingList>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.ALL])
    val invites: List<Invite>? = null
)
{
}