package com.smartcity.parking.router.handler;


import com.smartcity.parking.router.error.ErrorResponseBuilder;
import com.smartcity.parking.service.AccountService;
import com.smartcity.parking.service.dto.AccountRequest;
import com.smartcity.parking.service.dto.AccountResponse;
import com.smartcity.parking.service.exception.BusinessWebException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Component
public class AccountHandler {

    private final AccountService accountService;

    public AccountHandler(AccountService accountService) {
        this.accountService = accountService;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AccountRequest.class)
            .flatMap(accountService::create)
            .flatMap(response ->
                ServerResponse
                    .created(URI.create("/account" + response.getId()))
                    .body(Mono.just(response), AccountResponse.class))
            .onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return accountService.getById(UUID.fromString(serverRequest.pathVariable("id")))
            .flatMap(accountResponse -> ServerResponse.ok().body(Mono.just(accountResponse), AccountResponse.class))
            .onErrorResume(
                BusinessWebException.class,
                ErrorResponseBuilder::buildBusinessWebException

            ).onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return accountService.delete(UUID.fromString(serverRequest.pathVariable("id")))
            .flatMap(response -> ServerResponse.ok().build());
    }

}

