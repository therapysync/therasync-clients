package project.therasync.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.therasync.common.model.RequestContainer
import project.therasync.data.dto.AppointmentRequest
import project.therasync.data.dto.AppointmentResponse
import project.therasync.data.dto.AppointmentUpdateRequest
import project.therasync.service.AppointmentService

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments", description = "Управление записями на приём")
class AppointmentController(
    private val service: AppointmentService,
) {
    @Operation(summary = "Создать новую запись")
    @PostMapping
    fun create(requestContainer: RequestContainer<AppointmentRequest>): AppointmentResponse =
        service.createAppointment(requestContainer.request, requestContainer.clientId!!)

    @Operation(summary = "Обновить запись")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: AppointmentUpdateRequest,
    ): AppointmentResponse = service.updateAppointment(id, request)

    @Operation(summary = "Получить записи по психологу")
    @GetMapping("/psychologist")
    fun getByPsychologist(
        @RequestHeader("X-Client-Id") psychologistId: Long,
    ): List<AppointmentResponse> = service.getAppointmentsByPsychologist(psychologistId)

    @Operation(summary = "Получить записи по клиенту")
    @GetMapping("/client")
    fun getByClient(
        @RequestHeader("X-Client-Id") clientId: Long,
    ): List<AppointmentResponse> = service.getAppointmentsByClient(clientId)
}
