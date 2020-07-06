package com.smartcity.parking.service;

import com.smartcity.parking.service.dto.UserRequest;
import com.smartcity.parking.service.dto.UserResponse;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserResponse> create(UserRequest userRequest);

    Mono<UserResponse> getById(String id);

    Mono<Void> delete(String id);

}
