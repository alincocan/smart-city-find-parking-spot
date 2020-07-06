package com.smartcity.parking.router;

import com.smartcity.parking.router.handler.UserHandler;
import com.smartcity.parking.router.handler.CarHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerAccountFunction(UserHandler userHandler, CarHandler carHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/user/me"), userHandler::getById)
            .andRoute(RequestPredicates.DELETE("/user/"), userHandler::delete)
            .andRoute(RequestPredicates.GET("/user/cars"), carHandler::getByUser);
    }

}
