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
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import project.therasync.common.model.RequestContainer
import project.therasync.data.dto.TaskAnswerRequest
import project.therasync.data.dto.TaskRequest
import project.therasync.data.dto.TaskResponse
import project.therasync.data.model.TaskStatus
import project.therasync.service.TaskService
import java.time.LocalDate

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Задания между клиентом и психологом")
class TaskController(
    private val taskService: TaskService,
) {
    @Operation(summary = "Назначить новое задание клиенту")
    @PostMapping
    fun assignTask(requestContainer: RequestContainer<TaskRequest>): TaskResponse =
        taskService.assignTask(requestContainer.request, requestContainer.clientId!!)

    @Operation(summary = "Получить задания клиента")
    @GetMapping("/client/{id}")
    fun getTasksByClient(
        @PathVariable id: Long,
        @RequestParam(required = false) status: TaskStatus?,
    ): List<TaskResponse> = taskService.getTasksByClient(id, status)

    @Operation(summary = "Посмотреть задания в календаре")
    @GetMapping("/client/calendar")
    fun getCalendarTasks(
        @RequestHeader("X-Client-Id") clientId: Long,
        @RequestParam date: LocalDate,
    ): List<TaskResponse> = taskService.getTasksForCalendar(clientId, date)

    @Operation(summary = "Получить задания психолога")
    @GetMapping("/psychologist")
    fun getTasksByPsychologist(
        @RequestHeader("X-Client-Id") psyhologistId: Long,
        @RequestParam(required = false) status: TaskStatus?,
    ): List<TaskResponse> = taskService.getTasksByPsychologist(psyhologistId, status)

    @Operation(summary = "Ответить на задание")
    @PutMapping("/{id}/answer")
    fun answerTask(
        @PathVariable id: Long,
        @RequestBody request: TaskAnswerRequest,
    ): TaskResponse = taskService.answerTask(id, request)
}
