package project.therasync.data.dto

data class AssignClientRequest(
    val psychologistId: String,
    val clientId: String
)

data class ClientResponse(
    val clientId: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
