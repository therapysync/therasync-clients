package project.therasync.data.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,
    val taskText: String,
    val answerText: String? = null,
    val psychologistId: Long,
    val clientId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    var visibleFrom: LocalDate = LocalDate.now(),
    @Enumerated(EnumType.STRING)
    var taskStatus: TaskStatus = TaskStatus.ACTIVE,
)
