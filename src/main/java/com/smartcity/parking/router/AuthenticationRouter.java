package com.smartcity.parking.router;

import com.smartcity.parking.router.handler.AuthenticationHandler;
import com.smartcity.parking.router.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AuthenticationRouter {

    @Bean
    public RouterFunction<ServerResponse> routerAuthenticationFunction(AuthenticationHandler authenticationHandler,
        UserHandler userHandler
        ) {
        return RouterFunctions.route(RequestPredicates.POST("/auth/login"), authenticationHandler::login)
            .andRoute(RequestPredicates.POST("/auth/sign-up"), userHandler::create);
    }

}
