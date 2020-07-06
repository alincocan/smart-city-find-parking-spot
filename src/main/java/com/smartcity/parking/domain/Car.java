package com.smartcity.parking.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Car implements Entity {

    @Id
    private String id;
    private String registrationPlate;
    private String brand;
    private String model;
    private String color;
    private String accountId;

    public EntityType getType() {
        return EntityType.CAR;
    }
}
