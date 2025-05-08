package project.therasync.data.model

import jakarta.persistence.*

@Entity
data class PsychologistClient(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val psychologistId: Long,
    val clientId: Long
)
