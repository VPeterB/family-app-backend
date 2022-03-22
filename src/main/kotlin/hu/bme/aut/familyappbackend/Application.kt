package hu.bme.aut.familyappbackend

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
