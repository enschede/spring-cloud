package nl.marcenschede.tests.springcloud.springdata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@Enable
class SpringdataApplication

fun main(args: Array<String>) {
    runApplication<SpringdataApplication>(*args)
}
