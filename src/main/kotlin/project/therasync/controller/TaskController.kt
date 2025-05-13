package project.therasync.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.therasync.data.dto.TaskAnswerRequest
import project.therasync.data.dto.TaskRequest
import project.therasync.data.dto.TaskResponse
import project.therasync.service.TaskService

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Задания между клиентом и психологом")
class TaskController(
    private val taskService: TaskService
) {

    @Operation(summary = "Назначить новое задание клиенту")
    @PostMapping
    fun assignTask(@RequestBody request: TaskRequest): ResponseEntity<TaskResponse> =
        ResponseEntity.ok(taskService.assignTask(request))

    @Operation(summary = "Получить задания клиента")
    @GetMapping("/client/{id}")
    fun getTasksByClient(@PathVariable id: String): ResponseEntity<List<TaskResponse>> =
        ResponseEntity.ok(taskService.getTasksByClient(id))

    @Operation(summary = "Получить задания психолога")
    @GetMapping("/psychologist/{id}")
    fun getTasksByPsychologist(@PathVariable id: String): ResponseEntity<List<TaskResponse>> =
        ResponseEntity.ok(taskService.getTasksByPsychologist(id))

    @Operation(summary = "Ответить на задание")
    @PutMapping("/{id}/answer")
    fun answerTask(
        @PathVariable id: Long,
        @RequestBody request: TaskAnswerRequest
    ): ResponseEntity<TaskResponse> =
        ResponseEntity.ok(taskService.answerTask(id, request))
}
