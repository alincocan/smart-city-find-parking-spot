package com.smartcity.parking.service.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BusinessWebException {

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN);
    }

}
