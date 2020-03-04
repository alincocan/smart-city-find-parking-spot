package com.smartcity.parking.service;

import com.smartcity.parking.service.dto.CarRequest;
import com.smartcity.parking.service.dto.CarResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CarService {

    Mono<CarResponse> create(CarRequest carRequest);
    Mono<CarResponse> getById(UUID id);
    Flux<CarResponse> getByAccountId(UUID accountId);
    Mono<Void> delete(UUID id);
}
