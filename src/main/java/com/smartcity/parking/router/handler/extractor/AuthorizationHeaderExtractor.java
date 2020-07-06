package com.smartcity.parking.router.handler.extractor;

import com.smartcity.parking.security.TokenProvider;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

public class AuthorizationHeaderExtractor {

    public static Mono<String> extractUserId(ServerRequest serverRequest) {
        final String jwt = serverRequest.headers().header("Authorization").get(0).replace("Bearer ", "");
        final String userId = TokenProvider.getUserIdFromToken(jwt);
        return Mono.just(userId);
    }

}
