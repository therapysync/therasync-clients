package project.therasync.data.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "\"user\"")
data class User(
    @Id
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
