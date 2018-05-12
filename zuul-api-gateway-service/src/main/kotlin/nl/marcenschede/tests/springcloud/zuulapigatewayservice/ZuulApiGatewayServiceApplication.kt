package nl.marcenschede.tests.springcloud.zuulapigatewayservice

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.netflix.zuul.EnableZuulServer
import org.springframework.stereotype.Component

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
class ZuulApiGatewayServiceApplication

fun main(args: Array<String>) {
    runApplication<ZuulApiGatewayServiceApplication>(*args)
}


@Component
class ZuulLoggingFilter: ZuulFilter() {

    val logger = LoggerFactory.getLogger(ZuulLoggingFilter::class.java)

    override fun run(): Any? {

        val context = RequestContext.getCurrentContext().request

        logger.info("Request -> ${context}")
        logger.info("Request uri -> ${context.requestURI}")

        return null;
    }

    override fun shouldFilter(): Boolean {
        return true
    }

    override fun filterType(): String {
        return "pre"
    }

    override fun filterOrder(): Int {
        return 1
    }

}