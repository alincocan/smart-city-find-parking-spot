package com.smartcity.parking.service;

import com.smartcity.parking.service.dto.AccountRequest;
import com.smartcity.parking.service.dto.AccountResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountService {

    Mono<AccountResponse> create(AccountRequest accountRequest);
    Mono<AccountResponse> getById(UUID id);
    Mono<Void> delete(UUID id);

}
