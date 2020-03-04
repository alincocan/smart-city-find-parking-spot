package com.smartcity.parking.service.impl;

import com.smartcity.parking.domain.Account;
import com.smartcity.parking.domain.Car;
import com.smartcity.parking.repository.AccountRepository;
import com.smartcity.parking.repository.CarRepository;
import com.smartcity.parking.service.CarService;
import com.smartcity.parking.service.dto.CarRequest;
import com.smartcity.parking.service.dto.CarResponse;
import com.smartcity.parking.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("carService")
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final AccountRepository accountRepository;

    public CarServiceImpl(
        CarRepository carRepository,
        AccountRepository accountRepository
    ) {
        this.carRepository = carRepository;
        this.accountRepository = accountRepository;
    }

    @Override public Mono<CarResponse> create(CarRequest carRequest) {
        Car car = Car.builder()
            .registrationPlate(carRequest.getRegistrationPlate())
            .brand(carRequest.getBrand())
            .model(carRequest.getModel())
            .color(carRequest.getColor())
            .build();

        return carRepository.save(car)
            .map(this::buildResponse);
    }

    @Override public Mono<CarResponse> getById(UUID id) {
        return carRepository
            .findById(id)
            .map(this::buildResponse)
            .switchIfEmpty(Mono.error(new NotFoundException("Car not found")));
    }

    @Override public Flux<CarResponse> getByAccountId(UUID accountId) {
        return accountRepository
            .findById(accountId)
            .switchIfEmpty(Mono.error(new NotFoundException("Account not found")))
            .flatMapMany(account -> carRepository.findByAccountId(account.getId()))
            .map(this::buildResponse);
    }

    @Override public Mono<Void> delete(UUID id) {
        return carRepository.deleteById(id);
    }

    private CarResponse buildResponse(Car car) {
        return CarResponse
            .builder()
            .id(car.getId())
            .registrationPlate(car.getRegistrationPlate())
            .brand(car.getBrand())
            .model(car.getModel())
            .color(car.getColor())
            .build();
    }
}
