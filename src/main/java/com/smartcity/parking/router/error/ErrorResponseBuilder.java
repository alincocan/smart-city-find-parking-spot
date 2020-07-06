package com.smartcity.parking.router.error;

import com.smartcity.parking.service.exception.BusinessWebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
public class ErrorResponseBuilder {
    private static final String INTERNAL_SERVER_ERROR = "Internal server error";

    public static Mono<ServerResponse> buildBusinessWebException(BusinessWebException exception) {
        log.error(exception.getMessage());
        return ServerResponse.status(exception.getHttpStatus())
            .body(
                Mono.just(ErrorResponse.builder().message(exception.getMessage()).build()),
                ErrorResponse.class
            );
    }

    public static Mono<ServerResponse> buildInternalServerException(RuntimeException exception) {
        log.error(exception.getMessage());
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                Mono.just(ErrorResponse.builder().message(INTERNAL_SERVER_ERROR).build()),
                ErrorResponse.class
            );
    }
}
