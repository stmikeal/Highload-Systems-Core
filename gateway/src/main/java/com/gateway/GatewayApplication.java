package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

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
                        .filters(f -> f.rewritePath("/auth/(?<uri>.*)", "/${uri}"))
                        .uri("lb://idp-server"))
                .route("avito_route", r -> r.path("/api/**")
                        .filters(f -> f.rewritePath("/api/(?<uri>.*)", "/${uri}"))
                        .uri("lb://avito-client"))
                .route("account_route", r -> r.path("/user/**")
                        .filters(f -> f.rewritePath("/user/(?<uri>.*)", "/${uri}"))
                        .uri("lb://account-server"))
                .route("file_route", r -> r.path("/file/**")
                        .filters(f -> f.rewritePath("/file/(?<uri>.*)", "/${uri}"))
                        .uri("lb://file-server"))
                .build();
    }
}
