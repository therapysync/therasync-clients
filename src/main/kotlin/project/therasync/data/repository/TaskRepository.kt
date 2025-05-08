package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import project.therasync.data.model.Task

@Repository
interface TaskRepository : JpaRepository<Task, Long>