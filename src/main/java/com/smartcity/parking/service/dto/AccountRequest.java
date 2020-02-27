package com.smartcity.parking.service.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AccountRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

}
