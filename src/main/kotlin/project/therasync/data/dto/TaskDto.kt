package project.therasync.data.dto

data class TaskRequest(
    val title: String,
    val taskText: String,
    val psychologistId: String,
    val clientId: String
)

data class TaskAnswerRequest(
    val answerText: String
)

data class TaskResponse(
    val id: Long,
    val title: String,
    val taskText: String,
    val answerText: String?,
    val clientId: String,
    val psychologistId: String
)
