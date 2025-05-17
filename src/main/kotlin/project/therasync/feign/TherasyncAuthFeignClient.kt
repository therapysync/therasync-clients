package project.therasync.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.time.LocalDateTime

@FeignClient(
    name = "therasync-auth",
    url = "\${spring.cloud.openfeign.client.config.therasync-auth.url}",
)
interface TherasyncAuthFeignClient {
    @GetMapping("/api/users/{id}")
    fun getUser(
        @PathVariable id: Long,
    ): User

    @PostMapping("/api/users")
    fun getUsers(
        @RequestBody request: GetUsersRequest,
    ): List<User>
}

data class User(
    val id: Long = 0,
    var email: String,
    var image: String? = null,
    val firstName: String,
    val lastName: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    var role: Role? = Role.DEFAULT,
)

enum class Role {
    DEFAULT,
    CLIENT,
    PSYCHOLOGIST,
}

data class GetUsersRequest(
    val ids: List<Long>,
)
