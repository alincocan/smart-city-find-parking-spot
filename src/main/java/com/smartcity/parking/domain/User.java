package com.smartcity.parking.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;

}
