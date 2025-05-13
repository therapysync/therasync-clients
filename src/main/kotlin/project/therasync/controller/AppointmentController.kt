package project.therasync.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.therasync.data.dto.AppointmentRequest
import project.therasync.data.dto.AppointmentResponse
import project.therasync.data.dto.AppointmentUpdateRequest
import project.therasync.service.AppointmentService

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments", description = "Управление записями на приём")
class AppointmentController(
    private val service: AppointmentService
) {

    @Operation(summary = "Создать новую запись")
    @PostMapping
    fun create(@RequestBody request: AppointmentRequest): ResponseEntity<AppointmentResponse> =
        ResponseEntity.ok(service.createAppointment(request))

    @Operation(summary = "Обновить запись")
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: AppointmentUpdateRequest
    ): ResponseEntity<AppointmentResponse> =
        ResponseEntity.ok(service.updateAppointment(id, request))

    @Operation(summary = "Получить записи по психологу")
    @GetMapping("/psychologist/{id}")
    fun getByPsychologist(@PathVariable id: String): ResponseEntity<List<AppointmentResponse>> =
        ResponseEntity.ok(service.getAppointmentsByPsychologist(id))

    @Operation(summary = "Получить записи по клиенту")
    @GetMapping("/client/{id}")
    fun getByClient(@PathVariable id: String): ResponseEntity<List<AppointmentResponse>> =
        ResponseEntity.ok(service.getAppointmentsByClient(id))
}
