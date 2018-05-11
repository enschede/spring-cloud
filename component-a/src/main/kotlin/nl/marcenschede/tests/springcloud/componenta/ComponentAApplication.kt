package nl.marcenschede.tests.springcloud.componenta

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@EnableFeignClients
class ComponentAApplication {

    @Autowired
    lateinit var proxy: ForecastProxy

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): ForecastPresenter? {

        val forecast = proxy.createForecast(city)

        return forecast
    }

}

data class ForecastPresenter(val city: String, val longitude: String, val lattitude: String, var forecast: String = "", var port:Int = 0)

@FeignClient(name = "component-b")
@RibbonClient(name = "component-b")
interface ForecastProxy {

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): ForecastPresenter?
}

fun main(args: Array<String>) {
    runApplication<ComponentAApplication>(*args)
}
