package com.webchat.account.service.domain.service.inter;

import com.webchat.account.service.domain.models.Account;
import com.webchat.account.service.domain.models.MusicGenre;
import com.webchat.account.service.utils.request.SearchAccountsRequest;

import java.util.List;
import java.util.UUID;

public interface IAccountService {
    Account create(String userId);
    Account update(UUID accountId, Account account, List<MusicGenre> musicGenre, UUID workId);
    //Account update(String userId, Account account);
    Account getByAccId(UUID accountId);
    List<Account> getByUserId(String userId);
    Account addFriend(UUID userId, UUID accountId);
    List<Account> fetchAccounts(SearchAccountsRequest parameters);
    Account getFirmAccountById(UUID firmId);
    List<Account> fetchAccountsByIds(List<UUID> ids);
    List<Account> fetchAccountsWithUserSubscribe(UUID userId);
}
