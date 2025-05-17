package project.therasync.data.dto

import project.therasync.data.model.TaskStatus
import java.time.LocalDate

data class TaskRequest(
    val title: String,
    val taskText: String,
    val clientId: Long,
    val visibleFrom: LocalDate,
)

data class TaskAnswerRequest(
    val answerText: String,
)

data class TaskResponse(
    val id: Long,
    val title: String,
    val taskText: String,
    val answerText: String?,
    val clientId: Long,
    val psychologistId: Long,
    val taskStatus: TaskStatus,
)
