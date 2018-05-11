package nl.marcenschede.tests.springcloud.componenta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ComponentAApplication

fun main(args: Array<String>) {
    runApplication<ComponentAApplication>(*args)
}
