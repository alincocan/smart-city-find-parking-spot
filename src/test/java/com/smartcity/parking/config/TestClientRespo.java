package com.smartcity.parking.config;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class TestClientRespo implements ClientResponse {

    @Override public HttpStatus statusCode() {
        return null;
    }

    @Override public int rawStatusCode() {
        return 0;
    }

    @Override public Headers headers() {
        return null;
    }

    @Override public MultiValueMap<String, ResponseCookie> cookies() {
        return null;
    }

    @Override public ExchangeStrategies strategies() {
        return null;
    }

    @Override public <T> T body(BodyExtractor<T, ? super ClientHttpResponse> bodyExtractor) {
        return null;
    }

    @Override public <T> Mono<T> bodyToMono(Class<? extends T> aClass) {
        return null;
    }

    @Override public <T> Mono<T> bodyToMono(ParameterizedTypeReference<T> parameterizedTypeReference) {
        return null;
    }

    @Override public <T> Flux<T> bodyToFlux(Class<? extends T> aClass) {
        return null;
    }

    @Override public <T> Flux<T> bodyToFlux(ParameterizedTypeReference<T> parameterizedTypeReference) {
        return null;
    }

    @Override public Mono<Void> releaseBody() {
        return null;
    }

    @Override public <T> Mono<ResponseEntity<T>> toEntity(Class<T> aClass) {
        return null;
    }

    @Override public <T> Mono<ResponseEntity<T>> toEntity(ParameterizedTypeReference<T> parameterizedTypeReference) {
        return null;
    }

    @Override public <T> Mono<ResponseEntity<List<T>>> toEntityList(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> Mono<ResponseEntity<List<T>>> toEntityList(ParameterizedTypeReference<T> parameterizedTypeReference) {
        return null;
    }

    @Override public Mono<ResponseEntity<Void>> toBodilessEntity() {
        return null;
    }

    @Override public Mono<WebClientResponseException> createException() {
        return null;
    }

    @Override public String logPrefix() {
        return null;
    }


}
