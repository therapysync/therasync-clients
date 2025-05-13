package project.therasync.data.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    val id: String,

    val firstName: String,
    val lastName: String,
    val email: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
