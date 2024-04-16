package io.github.lucaspaixaodev.pochexadddclean.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.github.lucaspaixaodev.pochexadddclean"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}