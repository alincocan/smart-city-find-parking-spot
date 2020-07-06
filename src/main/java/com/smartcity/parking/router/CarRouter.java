package com.smartcity.parking.router;

import com.smartcity.parking.router.handler.CarHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CarRouter {

    @Bean
    public RouterFunction<ServerResponse> routerCarFunction(CarHandler carHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/car/{id}"), carHandler::getById)
            .andRoute(RequestPredicates.POST("/car"), carHandler::create)
            .andRoute(RequestPredicates.DELETE("/car/{id}"), carHandler::delete);
    }

}
