package project.therasync.data.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
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
    var psychologistId: Long,
    var clientId: Long,
    @Enumerated(EnumType.STRING)
    var status: Status,
)
