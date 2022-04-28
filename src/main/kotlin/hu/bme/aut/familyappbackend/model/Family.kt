package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "familys")
data class Family (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("id", required = true)
    val id: Int,

    @Column(name = "lastModTime")
    var lastModTime: Date? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.PERSIST])
    var users: List<User>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.PERSIST])
    var shoppingLists: List<ShoppingList>? = null,

    @OneToMany(mappedBy = "family", cascade = [CascadeType.PERSIST])
    var invites: List<Invite>? = null
)
{
}