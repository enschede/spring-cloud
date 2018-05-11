package nl.marcenschede.tests.springcloud.componentc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class ComponentCApplication {

    @GetMapping("/locate/{city}")
    fun findLocation(@PathVariable city: String): Coordinates =
            Coordinates(city, "52.219444", "6.896389")

    data class Coordinates(val city: String, val longitude: String, val lattitude: String)

}

fun main(args: Array<String>) {
    runApplication<ComponentCApplication>(*args)
}
