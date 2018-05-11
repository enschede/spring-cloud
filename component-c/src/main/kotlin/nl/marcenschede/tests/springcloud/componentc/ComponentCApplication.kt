package nl.marcenschede.tests.springcloud.componentc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ComponentCApplication

fun main(args: Array<String>) {
    runApplication<ComponentCApplication>(*args)
}
