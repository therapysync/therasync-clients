package project.therasync.data.model

import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,
    val taskText: String,
    val answerText: String? = null,

    val psychologistId: String,
    val clientId: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
