package project.therasync.service

import org.springframework.stereotype.Service
import project.therasync.data.repository.TaskRepository


@Service
class TaskService(
    private val taskRepository: TaskRepository,
) {
}