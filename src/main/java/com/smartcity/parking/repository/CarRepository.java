package com.smartcity.parking.repository;

import com.smartcity.parking.domain.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CarRepository extends ReactiveMongoRepository<Car, String> {

    Flux<Car> findByAccountId(String accountId);
}
