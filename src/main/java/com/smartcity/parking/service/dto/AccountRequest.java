package com.smartcity.parking.service.dto;

import lombok.Data;

@Data
public class AccountRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

}
