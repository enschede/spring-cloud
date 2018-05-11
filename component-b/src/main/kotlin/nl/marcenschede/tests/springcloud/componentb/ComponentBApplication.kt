package nl.marcenschede.tests.springcloud.componentb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@RestController
class ComponentBApplication {

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): Forecast? {

        val forcastResponse = RestTemplate().getForEntity("http://localhost:8200/locate/enschede", Forecast::class.java)

        val forecast = forcastResponse.body
        forecast?.forecast = "Sunny!"

        return forecast
    }

    data class Forecast(val city: String, val longitude: String, val lattitude: String, var forecast: String = "")
}

fun main(args: Array<String>) {
    runApplication<ComponentBApplication>(*args)
}
