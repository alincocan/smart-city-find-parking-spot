package com.smartcity.parking.service.converter;

import com.smartcity.parking.domain.Car;
import com.smartcity.parking.service.dto.CarRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CarRequestToCarConverter implements Converter<CarRequest, Car> {

    @NonNull
    @Override public Car convert(CarRequest carRequest) {
            return Car
                .builder()
                .brand(carRequest.getBrand())
                .model(carRequest.getModel())
                .color(carRequest.getColor())
                .registrationPlate(carRequest.getRegistrationPlate())
                .build();
    }
}
