package project.therasync.data.model

import java.time.LocalDate
import java.time.LocalTime
import jakarta.persistence.*

@Entity
data class Appointment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    @Enumerated(EnumType.STRING)
    val status: Status
)
