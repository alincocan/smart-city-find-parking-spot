package com.smartcity.parking.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CarRequest {

    private UUID id;
    private String registrationPlate;
    private String brand;
    private String model;
    private String color;
    private String accountId;

}
