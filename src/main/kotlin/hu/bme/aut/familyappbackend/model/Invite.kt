package hu.bme.aut.familyappbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "invites")
data class Invite(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    val id: Int,

    @Column(name = "lastModTime")
    var lastModTime: Date? = null,

    @ManyToOne
    @JoinColumn(name = "family")
    val family: Family? = null,

    @OneToOne(mappedBy = "invite")
    val user: User? = null
)
