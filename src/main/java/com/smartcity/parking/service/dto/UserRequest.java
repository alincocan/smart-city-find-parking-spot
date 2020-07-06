package com.smartcity.parking.service.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;

}
