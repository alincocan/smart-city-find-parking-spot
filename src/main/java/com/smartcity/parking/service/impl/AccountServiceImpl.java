package com.smartcity.parking.service.impl;

import com.smartcity.parking.domain.Account;
import com.smartcity.parking.repository.AccountRepository;
import com.smartcity.parking.service.AccountService;
import com.smartcity.parking.service.dto.AccountRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service("accountService")
public class AccountServiceImpl implements AccountService  {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override public Mono<Account> create(AccountRequest accountRequest) {
        Account account =
            Account.builder()
            .firstName(accountRequest.getFirstName())
            .lastName(accountRequest.getLastName())
            .email(accountRequest.getEmail())
            .phoneNo(accountRequest.getPhoneNo())
            .build();

        return accountRepository.save(account)
            .onError;
    }
}
