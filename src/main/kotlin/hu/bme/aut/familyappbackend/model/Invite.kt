package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "invites")
data class Invite(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    val ID: Int,

    @ManyToOne
    @JoinColumn(name = "family")
    val family: Family? = null,

    @OneToOne(mappedBy = "invite")
    val user: User? = null
)
