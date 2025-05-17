package project.therasync.common.model

data class RequestContainer<T>(
    val request: T,
    val clientId: Long?,
    val role: String?,
)
