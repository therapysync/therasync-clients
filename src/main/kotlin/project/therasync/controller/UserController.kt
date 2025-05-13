package project.therasync.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.therasync.data.dto.user.CreateUserRequest
import project.therasync.data.dto.user.UpdateUserRequest
import project.therasync.data.model.User
import project.therasync.service.UserService

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Управление пользователями")
class UserController(
    private val userService: UserService,
) {

    @Operation(summary = "Создать нового пользователя")
    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<User> =
        ResponseEntity.ok(userService.createUser(request))

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: String): ResponseEntity<User> =
        ResponseEntity.ok(userService.getUser(id))

    @Operation(summary = "Обновить пользователя")
    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: String,
        @RequestBody request: UpdateUserRequest,
    ): ResponseEntity<User> =
        ResponseEntity.ok(userService.updateUser(id, request))

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}
