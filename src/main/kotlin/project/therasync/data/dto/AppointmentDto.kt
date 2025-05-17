package project.therasync.data.dto

import project.therasync.data.model.Status
import java.time.LocalDate
import java.time.LocalTime

data class AppointmentRequest(
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val clientId: Long,
)

data class AppointmentUpdateRequest(
    val date: LocalDate?,
    val startTime: LocalTime?,
    val endTime: LocalTime?,
    val status: Status?,
)

data class AppointmentResponse(
    val id: Long,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val status: Status,
    val psychologistId: Long,
    val clientId: Long,
)
