package com.example.gateway.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class Route {

    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("post", r -> r.path("/post")
                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("http://localhost:8040"))
                .build();
    }
}