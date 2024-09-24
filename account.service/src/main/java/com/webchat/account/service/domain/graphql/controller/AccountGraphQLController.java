package com.webchat.account.service.domain.graphql.controller;

import com.webchat.account.service.domain.models.Account;
import com.webchat.account.service.domain.repository.AccountRepository;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class AccountGraphQLController {
    private final AccountRepository repository;

    public AccountGraphQLController(AccountRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Account accountById(@Arguments UUID id){
        return repository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Account> accounts(){
        return repository.findAll();
    }
}
