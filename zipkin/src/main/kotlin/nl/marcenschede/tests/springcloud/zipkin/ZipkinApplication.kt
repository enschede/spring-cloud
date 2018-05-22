package nl.marcenschede.tests.springcloud.zipkin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import zipkin.server.internal.EnableZipkinServer

@SpringBootApplication
@EnableZipkinServer
class ZipkinApplication

fun main(args: Array<String>) {
    runApplication<ZipkinApplication>(*args)
}
