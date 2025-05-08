package project.therasync

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TherasyncClientsApplication

fun main(args: Array<String>) {
    runApplication<TherasyncClientsApplication>(*args)
}
