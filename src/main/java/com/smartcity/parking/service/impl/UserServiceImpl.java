package com.smartcity.parking.service.impl;

import com.smartcity.parking.domain.User;
import com.smartcity.parking.repository.UserRepository;
import com.smartcity.parking.service.UserService;
import com.smartcity.parking.service.dto.UserRequest;
import com.smartcity.parking.service.dto.UserResponse;
import com.smartcity.parking.service.exception.AlreadyExistsException;
import com.smartcity.parking.service.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("accountService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override public Mono<UserResponse> create(UserRequest userRequest) {
        return userRepository.findByEmail(userRequest.getEmail())
            .map(this::throwAlreadyExistsException)
            .switchIfEmpty(
                userRepository.save(
                    User.builder()
                        .firstName(userRequest.getFirstName())
                        .lastName(userRequest.getLastName())
                        .email(userRequest.getEmail())
                        .phoneNo(userRequest.getPhoneNo())
                        .password(passwordEncoder.encode(userRequest.getPassword()))
                        .build()
                )
            ).map(this::buildResponse);
    }

    @Override public Mono<UserResponse> getById(String id) {
        return userRepository.findById(id)
            .map(this::buildResponse)
            .switchIfEmpty(Mono.error(new NotFoundException("Account not found")));
    }

    @Override public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }

    private UserResponse buildResponse(User user) {
        return UserResponse
            .builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .phoneNo(user.getPhoneNo())
            .build();
    }

    private User throwAlreadyExistsException(User user) {
        throw new AlreadyExistsException("User already exists");
    }
}
