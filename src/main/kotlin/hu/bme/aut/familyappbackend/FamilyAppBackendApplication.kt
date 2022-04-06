package hu.bme.aut.familyappbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class FamilyAppBackendApplication

fun main(args: Array<String>) {
    runApplication<FamilyAppBackendApplication>(*args)
}
