package nl.marcenschede.tests.springcloud.componentb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ComponentBApplication

fun main(args: Array<String>) {
    runApplication<ComponentBApplication>(*args)
}
