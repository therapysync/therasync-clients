package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.therasync.data.model.Task
import project.therasync.data.model.TaskStatus
import java.time.LocalDate

interface TaskRepository : JpaRepository<Task, Long> {
    fun findByClientId(clientId: Long): List<Task>

    fun findByClientIdAndTaskStatus(
        clientId: Long,
        taskStatus: TaskStatus,
    ): List<Task>

    fun findByPsychologistId(psychologistId: Long): List<Task>

    fun findByPsychologistIdAndTaskStatus(
        psychologistId: Long,
        taskStatus: TaskStatus,
    ): List<Task>

    fun findByClientIdAndVisibleFromLessThanEqualAndTaskStatus(
        clientId: Long,
        date: LocalDate,
        status: TaskStatus,
    ): List<Task>
}
