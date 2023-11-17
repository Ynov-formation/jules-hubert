package com.ynov.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("catalogue-service", r -> r.path("/catalogue/**")
                        .uri("lb://MS-CATALOGUE"))
                .route("security-service", r -> r.path("/auth/**")
                        .uri("http://localhost:8089"))
                .route("customer-service", r -> r.path("/customer/**")
                        .uri("lb://MS-CUSTOMER"))
                .route("order-service", r -> r.path("/order/**")
                        .uri("lb://MS-ORDER"))
                .build();
    }
}
