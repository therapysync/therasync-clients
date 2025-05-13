package project.therasync.data.dto.user

import io.swagger.v3.oas.annotations.media.Schema
import project.therasync.data.model.Role

@Schema(description = "Запрос на создание пользователя")
data class CreateUserRequest(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: Role
)

@Schema(description = "Запрос на обновление пользователя")
data class UpdateUserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: Role
)
