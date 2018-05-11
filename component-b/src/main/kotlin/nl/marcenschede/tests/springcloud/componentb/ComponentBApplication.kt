package nl.marcenschede.tests.springcloud.componentb

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableFeignClients
class ComponentBApplication {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var proxy: ForecastProxy

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): Forecast? {

        logger.info("Request on this node for city = {}", city)

        val forecast = proxy.createForecast(city)
        forecast?.forecast = "Warm and sunny!"

        return forecast
    }

}

data class Forecast(val city: String, val longitude: String, val lattitude: String, var forecast: String = "")

@FeignClient(name = "component-c", url = "localhost:8200")
interface ForecastProxy {

    @GetMapping("/locate/{city}")
    fun createForecast(@PathVariable city: String): Forecast?
}

fun main(args: Array<String>) {
    runApplication<ComponentBApplication>(*args)
}
