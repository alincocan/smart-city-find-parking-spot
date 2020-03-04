package com.smartcity.parking.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AccountResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

}
