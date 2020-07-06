package com.smartcity.parking.service;

import com.smartcity.parking.service.dto.CarRequest;
import com.smartcity.parking.service.dto.CarResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarService {

    Mono<CarResponse> create(CarRequest carRequest, String userId);

    Mono<CarResponse> getById(String id);

    Flux<CarResponse> getByUserId(String accountId);

    Mono<Void> delete(String id);
}
