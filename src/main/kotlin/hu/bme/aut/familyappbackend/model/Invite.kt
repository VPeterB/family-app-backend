package hu.bme.aut.familyappbackend.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param userID
 * @param familyID
 */
@Entity
@Table(name = "invites")
data class Invite(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    @field:JsonProperty("ID", required = true)
    val ID: Int,

    @ManyToOne
    @JoinColumn(name = "family")
    @field:JsonProperty("family")
    val family: Family? = null,

    @OneToOne(mappedBy = "invite")
    val user: User? = null
) {

}

