package com.smartcity.parking.service.impl;

import com.smartcity.parking.domain.Account;
import com.smartcity.parking.repository.AccountRepository;
import com.smartcity.parking.service.AccountService;
import com.smartcity.parking.service.dto.AccountRequest;
import com.smartcity.parking.service.dto.AccountResponse;
import com.smartcity.parking.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override public Mono<AccountResponse> create(AccountRequest accountRequest) {
        Account account =
            Account.builder()
                .firstName(accountRequest.getFirstName())
                .lastName(accountRequest.getLastName())
                .email(accountRequest.getEmail())
                .phoneNo(accountRequest.getPhoneNo())
                .build();

        return accountRepository.save(account)
            .map(this::buildResponse);
    }

    @Override public Mono<AccountResponse> getById(UUID id) {
        return accountRepository.findById(id)
            .map(this::buildResponse)
            .switchIfEmpty(Mono.error(new NotFoundException("Account not found")));
    }

    @Override public Mono<Void> delete(UUID id) {
        return accountRepository.deleteById(id);
    }

    private AccountResponse buildResponse(Account account) {
        return AccountResponse
            .builder()
            .id(account.getId())
            .firstName(account.getFirstName())
            .lastName(account.getLastName())
            .email(account.getEmail())
            .phoneNo(account.getPhoneNo())
            .build();
    }
}
