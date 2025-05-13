package project.therasync.data.dto

import project.therasync.data.model.Status
import java.time.LocalDate
import java.time.LocalTime

data class AppointmentRequest(
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val psychologistId: String,
    val clientId: String
)

data class AppointmentUpdateRequest(
    val date: LocalDate?,
    val startTime: LocalTime?,
    val endTime: LocalTime?,
    val status: Status?
)

data class AppointmentResponse(
    val id: Long,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val status: Status,
    val psychologistId: String,
    val clientId: String
)
