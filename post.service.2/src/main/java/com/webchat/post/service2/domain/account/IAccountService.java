package com.webchat.post.service2.domain.account;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
    List<UUID> getIdsOfFriendAccounts(UUID accountId);
}
