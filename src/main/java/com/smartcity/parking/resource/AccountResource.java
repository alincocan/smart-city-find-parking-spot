package com.smartcity.parking.resource;

import com.smartcity.parking.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account")
public class AccountResource {

    private final AccountService accountService;
    
   //constructor
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }
}
