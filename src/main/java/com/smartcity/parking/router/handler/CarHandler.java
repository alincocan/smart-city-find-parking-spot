package com.smartcity.parking.router.handler;

import com.smartcity.parking.router.error.ErrorResponseBuilder;
import com.smartcity.parking.service.CarService;
import com.smartcity.parking.service.dto.CarRequest;
import com.smartcity.parking.service.dto.CarResponse;
import com.smartcity.parking.service.exception.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.Principal;

import static com.smartcity.parking.router.handler.extractor.AuthorizationHeaderExtractor.extractUserId;

@Component
public class CarHandler {

    private final CarService carService;

    public CarHandler(CarService carService) {
        this.carService = carService;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CarRequest.class)
            .zipWith(serverRequest.principal().map(Principal::getName))
            .flatMap(tuple -> carService.create(tuple.getT1(), tuple.getT2()))
            .flatMap(response ->
                ServerResponse
                    .created(URI.create("/car/" + response.getId()))
                    .body(Mono.just(response), CarResponse.class)
            )
            .onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        final Mono<String> userId = extractUserId(serverRequest);

        return carService.getById(serverRequest.pathVariable("id"))
            .flatMap(response -> ServerResponse.ok().body(Mono.just(response), CarResponse.class))
            .onErrorResume(
                NotFoundException.class,
                ErrorResponseBuilder::buildBusinessWebException
            )
            .onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> getByUser(ServerRequest serverRequest) {
        final Flux<CarResponse> cars = extractUserId(serverRequest)
            .flatMapMany(carService::getByUserId);

        return ServerResponse
            .ok()
            .body(cars, CarResponse.class)
            .onErrorResume(
                NotFoundException.class,
                ErrorResponseBuilder::buildBusinessWebException
            )
            .onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return carService.delete(serverRequest.pathVariable("id"))
            .flatMap(response -> ServerResponse.ok().build());
    }

}
