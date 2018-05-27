package nl.marcenschede.tests.springcloud.componentb

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import brave.sampler.Sampler
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestParam


@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
class ComponentBApplication {

    val logger: Logger = LoggerFactory.getLogger(ComponentBApplication::class.java)

    @Autowired
    lateinit var proxy: ForecastProxy

    @Value("\${server.port}")
    var port: Int = 0

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): Forecast? {

        logger.info("Request on this node for city = {}", city)

        val location = proxy.createForecast(city)

        logger.info("Found forecast is {}", location)

        val cityValue = location?._embedded?.cities?.get(0)

        val forecast = Forecast(cityValue?.city?:"",
                cityValue?.lattitude?:"",
                cityValue?.longitude?:"")
        forecast?.forecast = "Warm and sunny!"
        forecast?.port = port

        return forecast
    }

    @Bean
    fun defaultSampler(): Sampler {
        return Sampler.ALWAYS_SAMPLE
    }
}

data class Forecast(val city: String, val longitude: String, val lattitude: String, var forecast: String = "", var port:Int = 0)

data class Location(val _embedded: Embedded?)
data class Embedded(val cities: List<City>)
data class City(val city: String?, val lattitude: String?, val longitude: String?)

@FeignClient(name = "springdata")
@RibbonClient(name = "springdata")
interface ForecastProxy {

    @GetMapping("citis/search/findByCity")
    fun createForecast(@RequestParam("city") city: String): Location?
}

fun main(args: Array<String>) {
    runApplication<ComponentBApplication>(*args)
}
