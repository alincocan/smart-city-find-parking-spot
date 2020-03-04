package com.smartcity.parking.service.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Slf4j
public class NotFoundException extends BusinessWebException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
