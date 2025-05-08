package project.therasync.controller

import gr.project.dualeasy.common.ApiException.Companion.NOT_FOUND_EXCEPTION
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import project.therasync.common.model.RequestContainer


@RestController
class TestController {

    @GetMapping("/test")
    fun test() = "Hello World"

    @GetMapping("/not-test")
    fun test1() = "Hello World"

    @PostMapping("/test")
    fun test2(
        @RequestBody person: Person
    ): Person = person

    @PostMapping("/test/test")
    fun test3(
        request: RequestContainer<Person2>
    ): RequestContainer<Person2> = request

    @GetMapping("/test/test/test")
    fun test3(
        @RequestHeader("X-Client-Id") clientId: String
    ): String = clientId

    @GetMapping("/test/test/test/test/{id}")
    fun test4(@PathVariable id: String): String {
        if (id == "1") {
            return "hello world"
        } else {
            throw NOT_FOUND_EXCEPTION
        }
    }
}

data class Person(
    val name: String,
    val age: Int,
    val clientId: String
)

data class Person2(
    val name: String,
    val age: Int,
)