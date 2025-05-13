package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.therasync.data.model.User

interface UserRepository : JpaRepository<User, String> {
    fun findByFirstNameContainingIgnoreCase(name: String): List<User>

}
