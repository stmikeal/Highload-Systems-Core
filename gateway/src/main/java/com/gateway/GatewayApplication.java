package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_route", r -> r.path("/auth/**")
                        .filters(f -> f.rewritePath("/auth/(?<login>.*)", "/${login}"))
                        .uri("lb://avito-client"))
                .route("avito_route", r -> r.path("/api/**")
                        .filters(f -> f.rewritePath("/api/(?<login>.*)", "/${login}"))
                        .uri("lb://avito-client"))
                .build();
    }
}