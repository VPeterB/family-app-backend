package hu.bme.aut.familyappbackend.model

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "events")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    val id: Int,

    @Column(name = "location")
    val location: String? = null,

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "endTime")
    var end: Timestamp? = null,

    @Column(name = "startTime")
    var start: Timestamp? = null,

    @ManyToOne
    @JoinColumn(name = "family")
    var family: Family? = null,

    @ManyToMany(mappedBy = "events")
    var users: List<User>? = null
)
