package nl.marcenschede.tests.springcloud.reactiverouter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


@SpringBootApplication
@EnableDiscoveryClient
@Configuration
class ReactiveRouterApplication {

    /** To test with:
     * curl -v http://localhost:8300/person
     * curl -v http://localhost:8300/person/5
     * curl -v http://localhost:8300/persons
     * curl -v -X POST http://localhost:8300/person
     * curl -v -X DELETE http://localhost:8300/person
     */

    @Bean
    fun router(handler: Handler) =
            router {
                ("/" and method(HttpMethod.GET)) {
                    serverRequest -> handler.handle(serverRequest)
                }
                ("/persons" and method(HttpMethod.GET))(handler::handleFlux)
                "/person".nest {
                    GET("/", handler::handle)
                    GET("/{id}", handler::handle)
                    POST("/", { _ -> ServerResponse.status(HttpStatus.CREATED).build() })
                }

                // Deze is BELANGRIJK; vang alles af wat je niet kent. Anders dikke exceptions
                "/**" {
                    _ -> ServerResponse.notFound().build()
                }

            }

}

@Component
class Handler {

    fun handle(serverRequest: ServerRequest): Mono<ServerResponse> {
        val builder = ServerResponse.ok()
        builder.headers {
            t -> t.add("myHeader", "myHeaderValue")
        }

        val response1 = builder.body("hello".toMono(), String::class.java)

        // Alternatieve body factories
        builder.body(BodyInserters.fromObject("Hello World"))

        return response1
    }

    fun handleFlux(serverRequest: ServerRequest): Mono<ServerResponse> {
        val builder = ServerResponse.ok()
        builder.contentType(MediaType.TEXT_EVENT_STREAM)

        val flux = Flux.just("Marc", "Kitty", "Yvette")

        return builder.body(flux, String::class.java)
    }

}


fun main(args: Array<String>) {
    runApplication<ReactiveRouterApplication>(*args)
}
