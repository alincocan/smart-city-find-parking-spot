package com.smartcity.parking.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessWebException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BusinessWebException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BusinessWebException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
