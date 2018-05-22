package nl.marcenschede.tests.springcloud.componentc

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import brave.sampler.Sampler
import org.springframework.context.annotation.Bean


@SpringBootApplication
@RestController
@EnableDiscoveryClient
class ComponentCApplication {

    val logger = LoggerFactory.getLogger(ComponentCApplication::class.java)

    @GetMapping("/locate/{city}")
    fun findLocation(@PathVariable city: String): Coordinates {

        val coordinates = Coordinates(city, "52.219444", "6.896389")
        logger.info("Found coordinates are {}", coordinates)
        return coordinates
    }

    @Bean
    fun defaultSampler(): Sampler {
        return Sampler.ALWAYS_SAMPLE
    }

    data class Coordinates(val city: String, val longitude: String, val lattitude: String)
}

fun main(args: Array<String>) {
    runApplication<ComponentCApplication>(*args)
}
