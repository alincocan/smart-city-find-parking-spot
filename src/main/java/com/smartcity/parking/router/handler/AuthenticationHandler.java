package com.smartcity.parking.router.handler;

import com.smartcity.parking.domain.AuthenticationResponse;
import com.smartcity.parking.router.error.ErrorResponseBuilder;
import com.smartcity.parking.service.AuthenticationService;
import com.smartcity.parking.service.dto.AuthenticationRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationHandler {

    private final AuthenticationService authenticationService;

    public AuthenticationHandler(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public Mono<ServerResponse> login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AuthenticationRequest.class)
            .flatMap(authenticationService::login)
            .flatMap(response ->
                ServerResponse
                    .ok()
                    .body(Mono.just(response), AuthenticationResponse.class))
            .onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

}
