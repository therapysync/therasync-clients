package project.therasync.service

import org.springframework.stereotype.Service
import project.therasync.data.dto.TaskAnswerRequest
import project.therasync.data.dto.TaskRequest
import project.therasync.data.dto.TaskResponse
import project.therasync.data.model.Task
import project.therasync.data.model.TaskStatus
import project.therasync.data.repository.TaskRepository
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class TaskService(
    private val taskRepository: TaskRepository,
) {
    fun getTasksForCalendar(
        clientId: Long,
        date: LocalDate,
    ): List<TaskResponse> =
        taskRepository
            .findByClientIdAndVisibleFromLessThanEqualAndTaskStatus(clientId, date, TaskStatus.ACTIVE)
            .map { it.toResponse() }

    fun assignTask(
        request: TaskRequest,
        psylogistId: Long,
    ): TaskResponse {
        val task =
            Task(
                title = request.title,
                taskText = request.taskText,
                psychologistId = psylogistId,
                clientId = request.clientId,
                taskStatus = TaskStatus.ACTIVE, // назначенное задание всегда активное
                visibleFrom = request.visibleFrom,
            )
        return taskRepository.save(task).toResponse()
    }

    fun answerTask(
        taskId: Long,
        request: TaskAnswerRequest,
    ): TaskResponse {
        val task =
            taskRepository
                .findById(taskId)
                .orElseThrow { IllegalArgumentException("Задание не найдено") }

        val updated =
            task.copy(
                answerText = request.answerText,
                updatedAt = LocalDateTime.now(),
                taskStatus = TaskStatus.COMPLETED, // при ответе статус становится "завершён"
            )
        return taskRepository.save(updated).toResponse()
    }

    fun getTasksByClient(
        clientId: Long,
        status: TaskStatus?,
    ): List<TaskResponse> {
        val tasks =
            if (status != null) {
                taskRepository.findByClientIdAndTaskStatus(clientId, status)
            } else {
                taskRepository.findByClientId(clientId)
            }
        return tasks.map { it.toResponse() }
    }

    fun getTasksByPsychologist(
        psychologistId: Long,
        status: TaskStatus?,
    ): List<TaskResponse> {
        val tasks =
            if (status != null) {
                taskRepository.findByPsychologistIdAndTaskStatus(psychologistId, status)
            } else {
                taskRepository.findByPsychologistId(psychologistId)
            }
        return tasks.map { it.toResponse() }
    }

    private fun Task.toResponse() =
        TaskResponse(
            id = id,
            title = title,
            taskText = taskText,
            answerText = answerText,
            clientId = clientId,
            psychologistId = psychologistId,
            taskStatus = taskStatus,
        )
}
