package com.smartcity.parking.it;

import com.smartcity.parking.domain.Car;
import com.smartcity.parking.domain.EntityType;
import com.smartcity.parking.remote.dto.PermissionResponse;
import com.smartcity.parking.remote.service.PermissionService;
import com.smartcity.parking.repository.CarRepository;
import com.smartcity.parking.service.dto.CarRequest;
import com.smartcity.parking.service.dto.CarResponse;
import com.smartcity.parking.token.GenerateToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest
public class CarResourceIT {

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private PermissionService permissionService;

    @Autowired
    private WebTestClient webTestClient;

    private final String userId = UUID.randomUUID().toString();

    private final String accessToken = GenerateToken.generateToken(userId, "email.com");

    private final String carId = UUID.randomUUID().toString();

    private final CarRequest carRequest =
        CarRequest
            .builder()
            .brand("Audi")
            .model("A4")
            .color("black")
            .registrationPlate("BV-95-AIC")
            .build();

    private final Car car =
        Car
            .builder()
            .id(carId)
            .brand("Audi")
            .model("A4")
            .color("black")
            .registrationPlate("BV-95-AIC")
            .build();

    private final PermissionResponse permissionResponse =
        PermissionResponse
            .builder()
            .externalId(carId)
            .type(EntityType.CAR.toString())
            .build();

    @Test
    public void when_createCar_expect_ok() {

        when(carRepository.save(any())).thenReturn(Mono.just(car));
        when(permissionService.create(carId, EntityType.CAR)).thenReturn(Mono.just(permissionResponse));

        webTestClient
            .post()
            .uri("/car")
            .body(Mono.just(carRequest), CarRequest.class)
            .header("Authorization", "Bearer " + accessToken)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(CarResponse.class)
            .value(response -> {
                Assertions.assertEquals(carRequest.getBrand(), response.getBrand());
                Assertions.assertEquals(carRequest.getModel(), response.getModel());
                Assertions.assertEquals(carRequest.getColor(), response.getColor());
                Assertions.assertEquals(carRequest.getRegistrationPlate(), response.getRegistrationPlate());
            });
    }
}
