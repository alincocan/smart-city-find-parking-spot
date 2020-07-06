package com.smartcity.parking.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {

    private String id;
    private String registrationPlate;
    private String brand;
    private String model;
    private String color;

}
