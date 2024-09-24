package com.webchat.post.service2.domain.account;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService{
    @Override
    public List<UUID> getIdsOfFriendAccounts(UUID accountId) {
        return null;
    }
}
