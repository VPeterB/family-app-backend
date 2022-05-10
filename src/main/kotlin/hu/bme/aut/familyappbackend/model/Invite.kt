package hu.bme.aut.familyappbackend.model

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "invites")
data class Invite(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    val id: Int,

    @Column(name = "lastModTime")
    var lastModTime: Timestamp? = null,

    @ManyToOne
    @JoinColumn(name = "family")
    var family: Family? = null,

    @OneToOne(mappedBy = "invite")
    var user: User? = null
)
