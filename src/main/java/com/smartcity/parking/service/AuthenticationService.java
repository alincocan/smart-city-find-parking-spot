package com.smartcity.parking.service;

import com.smartcity.parking.domain.AuthenticationResponse;
import com.smartcity.parking.service.dto.AuthenticationRequest;
import reactor.core.publisher.Mono;

public interface AuthenticationService {

    Mono<AuthenticationResponse> login(AuthenticationRequest authenticationRequest);

}
