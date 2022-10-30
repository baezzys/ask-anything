package Router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

class Gateway {

@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
        .route("r1", r -> r.path("/login").uri("http://localhost:8081"))
        .build();
        }
}