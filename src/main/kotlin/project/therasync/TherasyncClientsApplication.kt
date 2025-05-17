package project.therasync

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class TherasyncClientsApplication

fun main(args: Array<String>) {
    runApplication<TherasyncClientsApplication>(*args)
}
