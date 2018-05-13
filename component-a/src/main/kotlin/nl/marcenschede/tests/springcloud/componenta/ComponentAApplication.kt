package nl.marcenschede.tests.springcloud.componenta

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import com.netflix.ribbon.proxy.annotation.Hystrix
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
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
@EnableHystrix
class ComponentAApplication {

    @Autowired
    lateinit var proxy: ForecastProxy

    @GetMapping("/forecast/{city}")
    fun createForecast(@PathVariable city: String): ForecastPresenter? =
        proxy.createForecast(city)

    @GetMapping("/error/{city}")
    @HystrixCommand(fallbackMethod = "fallbackForecast")
    fun errorForecast(@PathVariable city: String): ForecastPresenter? =
        throw RuntimeException()

    fun fallbackForecast(@PathVariable city: String): ForecastPresenter? =
        ForecastPresenter("none", "0", "0")
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
