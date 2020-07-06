package com.smartcity.parking.service.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends BusinessWebException {

    public AuthenticationException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }

}
