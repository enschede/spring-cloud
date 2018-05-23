package nl.marcenschede.tests.springcloud.reactiverouter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono


@SpringBootApplication
@EnableDiscoveryClient
@Configuration
class ReactiveRouterApplication {

    /** To test with:
     * curl -v http://localhost:8300/person
     * curl -v http://localhost:8300/person/5
     * curl -v -X POST http://localhost:8300/person
     * curl -v -X DELETE http://localhost:8300/person
     */

    @Bean
    fun router(handler: Handler) =
            router {
                ("/" and method(HttpMethod.GET)) {
                    serverRequest -> handler.handle(serverRequest)
                }
                "/person".nest {
                    GET("/", handler::handle)
                    GET("/{id}", handler::handle)
                    POST("/", { serverRequest -> ServerResponse.status(HttpStatus.CREATED).build() })
                }

                // Deze is BELANGRIJK; vang alles af wat je niet kent. Anders dikke exceptions
                "/**" {
                    serverRequest -> ServerResponse.notFound().build()
                }

            }

}

@Component
class Handler {

    fun handle(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().build()
    }

}


fun main(args: Array<String>) {
    runApplication<ReactiveRouterApplication>(*args)
}
