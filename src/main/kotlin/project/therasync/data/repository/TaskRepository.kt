package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.therasync.data.model.Task

interface TaskRepository : JpaRepository<Task, Long> {
    fun findByClientId(clientId: String): List<Task>
    fun findByPsychologistId(psychologistId: String): List<Task>
}
