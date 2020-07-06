package com.smartcity.parking.router.error;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ErrorResponse {

    private String severity;
    private  String message;

}
