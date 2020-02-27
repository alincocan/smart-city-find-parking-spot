package com.smartcity.parking.service;

import com.smartcity.parking.domain.Account;
import com.smartcity.parking.service.dto.AccountRequest;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<Account> create(AccountRequest accountRequest);

}
