package com.webchat.account.service.domain.graphql.resolver;

import com.webchat.account.service.domain.models.Account;
import com.webchat.account.service.domain.repository.AccountRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountQueryResolver implements GraphQLQueryResolver {
    private final AccountRepository repository;

    public List<Account> getAccounts(){
        return repository.findAll();
    }

    public Account getAccountById(UUID id){
        return repository.findById(id).orElse(null);
    }
}
