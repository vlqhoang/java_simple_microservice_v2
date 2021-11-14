package com.practice.microservice.netflixzuulapigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // build custom redirect (dummy example)
                .route((predicateSpec) -> predicateSpec.path("/get") // matching requests by a regex (here is to search for a String) from request path
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .addRequestHeader("MyHeader", "MyURI")
                                .addRequestParameter("Param", "MyParamValue"))
                        .uri("http://httpbin:80"))

                // build custom redirect for currency exchange service and perform load balancing with matched service from eureka.
                // Can test with -> http://localhost:8765/currency-exchange/from/USD/to/INR
                .route((predicateSpec) -> predicateSpec.path("/currency-exchange/**")
                        .uri("lb://currency-exchange-service"))

                // build custom redirect for conversion service and perform load balancing with matched service from eureka.
                // Can test with -> http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/100
                .route((predicateSpec -> predicateSpec.path("/currency-conversion/**")
                        .uri("lb://currency-conversion-service")))

                // build a custom redirect - rewrite path example
                // Can test with -> http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/100
                .route((predicateSpec -> predicateSpec.path("/currency-conversion-new/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath(
                                "/currency-conversion-new/(?<segment>.*)",
                                "/currency-conversion/${segment}"))
                        .uri("lb://currency-conversion-service")))

                // return customized Router
                .build();
    }
}
