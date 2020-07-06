package com.smartcity.parking.service.converter;

import com.smartcity.parking.domain.Car;
import com.smartcity.parking.service.dto.CarResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CarToCarResponseConverter implements Converter<Car, CarResponse> {

    @NonNull
    @Override public CarResponse convert(Car car) {
        return CarResponse
            .builder()
            .id(car.getId())
            .brand(car.getBrand())
            .model(car.getModel())
            .color(car.getColor())
            .registrationPlate(car.getRegistrationPlate())
            .build();
    }
}
