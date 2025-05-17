package project.therasync.data.dto

data class AssignClientRequest(
    val psychologistId: Long,
    val clientId: Long
)

data class ClientResponse(
    val clientId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
)
