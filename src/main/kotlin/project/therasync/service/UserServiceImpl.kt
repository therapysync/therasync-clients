package project.therasync.service

import org.springframework.stereotype.Service
import project.therasync.data.model.User
import project.therasync.data.repository.UserRepository
import java.time.LocalDateTime
import jakarta.persistence.EntityNotFoundException
import project.therasync.data.dto.user.CreateUserRequest
import project.therasync.data.dto.user.UpdateUserRequest
import project.therasync.data.model.Role

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun createUser(request: CreateUserRequest): User {
        val user = User(
            id = request.id,
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            role = Role.CLIENT,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        return userRepository.save(user)
    }

    fun getUser(id: String): User {
        return userRepository.findById(id)
            .orElseThrow { EntityNotFoundException("User with ID $id not found") }
    }

    fun updateUser(id: String, request: UpdateUserRequest): User {
        val user = getUser(id).copy(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            role = request.role,
            updatedAt = LocalDateTime.now()
        )
        return userRepository.save(user)
    }

    fun deleteUser(id: String) {
        if (!userRepository.existsById(id)) {
            throw EntityNotFoundException("User with ID $id not found")
        }
        userRepository.deleteById(id)
    }
}
