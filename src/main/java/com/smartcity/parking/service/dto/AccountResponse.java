package com.smartcity.parking.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class AccountResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

}
