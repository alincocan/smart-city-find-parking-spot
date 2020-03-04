package com.smartcity.parking.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Car {

    private UUID id;
    private String registrationPlate;
    private String brand;
    private String model;
    private String color;
}
