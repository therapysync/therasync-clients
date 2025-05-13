package project.therasync.service

import org.springframework.stereotype.Service
import project.therasync.data.dto.TaskAnswerRequest
import project.therasync.data.dto.TaskRequest
import project.therasync.data.dto.TaskResponse
import project.therasync.data.model.Task
import project.therasync.data.repository.TaskRepository

import java.time.LocalDateTime

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {

    fun assignTask(request: TaskRequest): TaskResponse {
        val task = Task(
            title = request.title,
            taskText = request.taskText,
            psychologistId = request.psychologistId,
            clientId = request.clientId
        )
        return taskRepository.save(task).toResponse()
    }

    fun getTasksByClient(clientId: String): List<TaskResponse> {
        return taskRepository.findByClientId(clientId).map { it.toResponse() }
    }

    fun getTasksByPsychologist(psychologistId: String): List<TaskResponse> {
        return taskRepository.findByPsychologistId(psychologistId).map { it.toResponse() }
    }

    fun answerTask(taskId: Long, request: TaskAnswerRequest): TaskResponse {
        val task = taskRepository.findById(taskId)
            .orElseThrow { IllegalArgumentException("Задание не найдено") }

        val updated = task.copy(
            answerText = request.answerText,
            updatedAt = LocalDateTime.now()
        )
        return taskRepository.save(updated).toResponse()
    }

    private fun Task.toResponse() = TaskResponse(
        id = id,
        title = title,
        taskText = taskText,
        answerText = answerText,
        clientId = clientId,
        psychologistId = psychologistId
    )
}
