package com.smartcity.parking.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CarResponse {

    private UUID id;
    private String registrationPlate;
    private String brand;
    private String model;
    private String color;

}
