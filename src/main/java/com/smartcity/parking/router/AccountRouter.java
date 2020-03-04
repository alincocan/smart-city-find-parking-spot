package com.smartcity.parking.router;

import com.smartcity.parking.router.handler.AccountHandler;
import com.smartcity.parking.router.handler.CarHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AccountRouter {

    @Bean
    public RouterFunction<ServerResponse> routerAccountFunction(AccountHandler accountHandler, CarHandler carHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/account/{id}"), accountHandler::getById)
            .andRoute(RequestPredicates.POST("/account"), accountHandler::create)
            .andRoute(RequestPredicates.DELETE("/account/{id}"), accountHandler::delete)
            .andRoute(RequestPredicates.GET("/account/{id}/cars"), carHandler::getByAccount);
    }

}
