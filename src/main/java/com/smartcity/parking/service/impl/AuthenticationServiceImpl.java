package com.smartcity.parking.service.impl;

import com.smartcity.parking.domain.AuthenticationResponse;
import com.smartcity.parking.repository.UserRepository;
import com.smartcity.parking.security.TokenProvider;
import com.smartcity.parking.service.AuthenticationService;
import com.smartcity.parking.service.dto.AuthenticationRequest;
import com.smartcity.parking.service.exception.AuthenticationException;
import com.smartcity.parking.service.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override public Mono<AuthenticationResponse> login(AuthenticationRequest authenticationRequest) {
        return userRepository.findByEmail(authenticationRequest.getUsername())
            .switchIfEmpty(Mono.error(new NotFoundException("User not found")))
            .filter(user -> passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()))
            .switchIfEmpty(Mono.error(new AuthenticationException("Invalid password")))
            .map(TokenProvider::generateToken)
            .map(AuthenticationResponse::new);
    }

}
