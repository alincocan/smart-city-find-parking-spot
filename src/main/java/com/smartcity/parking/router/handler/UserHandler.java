package com.smartcity.parking.router.handler;


import com.smartcity.parking.router.error.ErrorResponseBuilder;
import com.smartcity.parking.service.UserService;
import com.smartcity.parking.service.dto.UserRequest;
import com.smartcity.parking.service.dto.UserResponse;
import com.smartcity.parking.service.exception.BusinessWebException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;

import static com.smartcity.parking.router.handler.extractor.AuthorizationHeaderExtractor.extractUserId;

@Component
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(UserRequest.class)
            .flatMap(userService::create)
            .flatMap(response ->
                ServerResponse
                    .created(URI.create("/user/me"))
                    .body(Mono.just(response), UserResponse.class))
            .onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return serverRequest.principal()
            .map(Principal::getName)
            .map(userService::getById)
            .flatMap(accountResponse -> ServerResponse.ok().body(Mono.just(accountResponse), UserResponse.class))
            .onErrorResume(
                BusinessWebException.class,
                ErrorResponseBuilder::buildBusinessWebException

            ).onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return extractUserId(serverRequest)
            .map(userService::delete)
            .flatMap(response -> ServerResponse.ok().build());
    }

}

