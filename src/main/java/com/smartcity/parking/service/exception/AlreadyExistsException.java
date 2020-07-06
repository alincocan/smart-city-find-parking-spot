package com.smartcity.parking.service.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends BusinessWebException {

    public AlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
