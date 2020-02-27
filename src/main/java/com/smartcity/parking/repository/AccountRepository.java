package com.smartcity.parking.repository;

import com.smartcity.parking.domain.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface AccountRepository extends ReactiveMongoRepository<Account, UUID> {
}
