package hu.bme.aut.familyappbackend.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.aut.familyappbackend.model.InlineResponse200
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
 * @param email
 * @param password
 * @param phonenumber
 * @param firstname
 * @param lastname
 * @param username
 * @param category
 * @param profilepicture
 * @param familyID
 * @param inviteIDs
 * @param shoppinglistIDs
 */
@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("ID", required = true)
    val ID: Int,

    @Column(name = "email", nullable = false)
    @field:JsonProperty("email", required = true)
    val email: String,

    @Column(name = "password", nullable = false)
    @field:JsonProperty("password", required = true)
    val password: String,

    @Column(name = "phonenumber")
    @field:JsonProperty("phonenumber")
    val phonenumber: String? = null,

    @Column(name = "firstname")
    @field:JsonProperty("firstname")
    val firstname: String? = null,

    @Column(name = "lastname")
    @field:JsonProperty("lastname")
    val lastname: String? = null,

    @Column(name = "username")
    @field:JsonProperty("username")
    val username: String? = null,

    @Column(name = "category")
    @field:JsonProperty("category")
    val category: String? = null,

    @ManyToOne
    @JoinColumn(name = "family")
    @field:JsonProperty("family")
    val family: Family? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "invite")
    val invite: Invite? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "user_shoppinglist",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "ID")],
        inverseJoinColumns = [JoinColumn(name = "sl_id", referencedColumnName = "ID")]
    )
    val shoppingLists: List<ShoppingList>? = null
) {


}

