package hu.bme.aut.familyappbackend

import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
@ComponentScan(basePackages = ["hu.bme.aut.familyappbackend", "hu.bme.aut.familyappbackend.api", "hu.bme.aut.familyappbackend.model"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
