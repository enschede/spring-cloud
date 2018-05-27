package nl.marcenschede.tests.springcloud.springdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class SpringdataApplication

fun main(args: Array<String>) {
    runApplication<SpringdataApplication>(*args)
}
