package hu.bme.aut.familyappbackend.model

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Int,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "phonenumber")
    val phonenumber: String? = null,

    @Column(name = "firstname")
    val firstname: String? = null,

    @Column(name = "lastname")
    val lastname: String? = null,

    @Column(name = "username")
    val username: String? = null,

    @Column(name = "category")
    val category: String? = null,

    @Column(name = "lastModTime")
    var lastModTime: Timestamp? = null,

    @ManyToOne
    @JoinColumn(name = "family")
    var family: Family? = null,

    @OneToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "invite")
    var invite: Invite? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "user_shoppinglist",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "sl_id", referencedColumnName = "id")]
    )
    var shoppingLists: List<ShoppingList>? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST])
    @JoinTable(
        name = "user_event",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "event_id", referencedColumnName = "id")]
    )
    var events: List<Event>? = null
)
{
}