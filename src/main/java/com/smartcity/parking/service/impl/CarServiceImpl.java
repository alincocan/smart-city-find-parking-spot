package com.smartcity.parking.service.impl;

import com.smartcity.parking.domain.Car;
import com.smartcity.parking.remote.service.PermissionService;
import com.smartcity.parking.repository.CarRepository;
import com.smartcity.parking.service.CarService;
import com.smartcity.parking.service.converter.CarRequestToCarConverter;
import com.smartcity.parking.service.converter.CarToCarResponseConverter;
import com.smartcity.parking.service.dto.CarRequest;
import com.smartcity.parking.service.dto.CarResponse;
import com.smartcity.parking.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service("carService")
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PermissionService permissionService;
    private final CarToCarResponseConverter carToCarResponseConverter;
    private final CarRequestToCarConverter carRequestToCarConverter;

    public CarServiceImpl(
        CarRepository carRepository,
        PermissionService permissionService,
        CarToCarResponseConverter carToCarResponseConverter,
        CarRequestToCarConverter carRequestToCarConverter
    ) {
        this.carRepository = carRepository;
        this.permissionService = permissionService;
        this.carToCarResponseConverter = carToCarResponseConverter;
        this.carRequestToCarConverter = carRequestToCarConverter;
    }

    @Override public Mono<CarResponse> create(CarRequest carRequest, String userId) {
        final Car car = carRequestToCarConverter.convert(carRequest);
        car.setAccountId(userId);

        return carRepository.save(car)
            .flatMap(this::createPermissionAndReturnCar)
            .map(carToCarResponseConverter::convert);
    }

    @Override public Mono<CarResponse> getById(String id) {
        return permissionService.getByExternalId(id)
            .flatMap(carRepository::findById)
            .switchIfEmpty(Mono.error(new NotFoundException("Car not found")))
            .map(carToCarResponseConverter::convert);
    }

    @Override public Flux<CarResponse> getByUserId(String accountId) {
        return permissionService.getByUserId(accountId).flux()
            .zipWith(carRepository.findByAccountId(accountId))
            .filter(tuple -> tuple.getT1().contains(tuple.getT2().getId()))
            .map(Tuple2::getT2)
            .map(carToCarResponseConverter::convert);
    }

    @Override public Mono<Void> delete(String id) {
        return carRepository.deleteById(id);
    }

    private Mono<Car> createPermissionAndReturnCar(Car car) {
        return permissionService.create(car.getId(), car.getType())
            .zipWith(Mono.just(car))
            .map(Tuple2::getT2);
    }
}
