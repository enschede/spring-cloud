package nl.marcenschede.tests.springcloud.componenta

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableDiscoveryClient
class ComponentAApplication {

    val logger = LoggerFactory.getLogger(ComponentAApplication::class.java)

    @Autowired
    lateinit var proxy: ForecastProxy

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): ForecastPresenter? {

        val forecast = proxy.createForecast(city)

        logger.info("Forecast to present is {}", forecast)

        return forecast
    }

}

data class ForecastPresenter(val city: String, val longitude: String, val lattitude: String, var forecast: String = "", var port:Int = 0)

@FeignClient(name = "zuul-api-gateway-service")
@RibbonClient(name = "component-b")
interface ForecastProxy {

    @GetMapping("/component-b/forecast/{city}")
    fun createForecast(@PathVariable city: String): ForecastPresenter?
}

fun main(args: Array<String>) {
    runApplication<ComponentAApplication>(*args)
}
