package com.smartcity.parking.router.error;

import com.smartcity.parking.service.exception.BusinessWebException;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ErrorResponseBuilder {
    private static final String INTERNAL_SERVER_ERROR = "Internal server error";

    public static Mono<ServerResponse> buildBusinessWebException(BusinessWebException exception) {
        return ServerResponse.status(exception.getHttpStatus())
            .body(
                Mono.just(ErrorResponse.builder().message(exception.getMessage()).build()),
                ErrorResponse.class
            );
    }

    public static Mono<ServerResponse> buildInternalServerException(RuntimeException exception) {
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                Mono.just(ErrorResponse.builder().message(INTERNAL_SERVER_ERROR).build()),
                ErrorResponse.class
            );
    }
}
