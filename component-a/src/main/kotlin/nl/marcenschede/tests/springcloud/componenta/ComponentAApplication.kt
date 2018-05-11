package nl.marcenschede.tests.springcloud.componenta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@RestController
class ComponentAApplication {

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): ForecastPresenter? {

        val forcastResponse =
                RestTemplate().getForEntity("http://localhost:8100/forecast/{city}", ForecastPresenter::class.java, city)

        val forecast = forcastResponse.body

        return forecast
    }

    data class ForecastPresenter(val city: String, val longitude: String, val lattitude: String, var forecast: String = "")

}

fun main(args: Array<String>) {
    runApplication<ComponentAApplication>(*args)
}
