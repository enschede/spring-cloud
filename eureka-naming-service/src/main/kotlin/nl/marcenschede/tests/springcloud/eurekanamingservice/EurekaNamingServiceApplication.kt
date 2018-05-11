package nl.marcenschede.tests.springcloud.eurekanamingservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class EurekaNamingServiceApplication

fun main(args: Array<String>) {
    runApplication<EurekaNamingServiceApplication>(*args)
}
