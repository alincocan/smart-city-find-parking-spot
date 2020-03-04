package com.smartcity.parking.repository;

import com.smartcity.parking.domain.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface CarRepository extends ReactiveMongoRepository<Car, UUID> {

    Flux<Car> findByAccountId(UUID accountId);
}
