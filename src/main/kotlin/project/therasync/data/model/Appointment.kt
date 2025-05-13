package project.therasync.data.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
data class Appointment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var date: LocalDate,
    var startTime: LocalTime,
    var endTime: LocalTime,

    var psychologistId: String,
    var clientId: String,

    @Enumerated(EnumType.STRING)
    var status: Status
)
