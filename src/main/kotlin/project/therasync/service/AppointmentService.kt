package project.therasync.service

import org.springframework.stereotype.Service
import project.therasync.data.dto.AppointmentRequest
import project.therasync.data.dto.AppointmentResponse
import project.therasync.data.dto.AppointmentUpdateRequest
import project.therasync.data.model.Appointment
import project.therasync.data.model.Status
import project.therasync.data.repository.AppointmentRepository

@Service
class AppointmentService(
    private val repository: AppointmentRepository
) {

    fun createAppointment(req: AppointmentRequest): AppointmentResponse {
        val appointment = Appointment(
            date = req.date,
            startTime = req.startTime,
            endTime = req.endTime,
            psychologistId = req.psychologistId,
            clientId = req.clientId,
            status = Status.BOOKED
        )
        return repository.save(appointment).toResponse()
    }

    fun updateAppointment(id: Long, req: AppointmentUpdateRequest): AppointmentResponse {
        val appointment = repository.findById(id).orElseThrow()
        req.date?.let { appointment.date = it }
        req.startTime?.let { appointment.startTime = it }
        req.endTime?.let { appointment.endTime = it }
        req.status?.let { appointment.status = it }

        return repository.save(appointment).toResponse()
    }

    fun getAppointmentsByPsychologist(id: String): List<AppointmentResponse> =
        repository.findByPsychologistId(id).map { it.toResponse() }

    fun getAppointmentsByClient(id: String): List<AppointmentResponse> =
        repository.findByClientId(id).map { it.toResponse() }

    private fun Appointment.toResponse() = AppointmentResponse(
        id = id,
        date = date,
        startTime = startTime,
        endTime = endTime,
        status = status,
        psychologistId = psychologistId,
        clientId = clientId
    )
}
