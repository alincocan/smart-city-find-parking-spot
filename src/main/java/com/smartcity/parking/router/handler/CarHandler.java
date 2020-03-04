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
import java.util.UUID;

@Component
public class CarHandler {

    private final CarService carService;

    public CarHandler(CarService carService) {
        this.carService = carService;
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CarRequest.class)
            .flatMap(carService::create)
            .flatMap(response ->
                ServerResponse
                    .created(URI.create("/car" + response.getId()))
                    .body(Mono.just(response), CarResponse.class)
            )
            .onErrorResume(
                RuntimeException.class,
                ErrorResponseBuilder::buildInternalServerException
            );
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        return carService
            .getById(UUID.fromString(serverRequest.pathVariable("id")))
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

    public Mono<ServerResponse> getByAccount(ServerRequest serverRequest) {
        Flux<CarResponse> cars = carService
            .getByAccountId(UUID.fromString(serverRequest.pathVariable("accountId")));

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
        return carService.delete(UUID.fromString(serverRequest.pathVariable("id")))
            .flatMap(response -> ServerResponse.ok().build());
    }

}
