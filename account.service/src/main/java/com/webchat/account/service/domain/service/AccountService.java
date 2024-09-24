package com.webchat.account.service.domain.service;

import com.webchat.account.service.domain.models.Account;
import com.webchat.account.service.domain.models.AccountType;
import com.webchat.account.service.domain.models.MusicGenre;
import com.webchat.account.service.domain.repository.AccountRepository;
import com.webchat.account.service.domain.service.inter.IAccountService;
import com.webchat.account.service.utils.exception.ApiRequestException;
import com.webchat.account.service.utils.request.SearchAccountsRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {
    private final AccountRepository repository;
    @Override
    public Account create(String userId) {
        return repository.save(new Account(userId));
    }

    @Override
    public Account update(UUID accountId, Account account, List<MusicGenre> musicGenre, UUID workId) {
        Account myAcc = getByAccId(accountId);

        account.setId(accountId);
        account.setUserId(myAcc.getUserId());
        account.setFriends(myAcc.getFriends());
        account.setMusicGenre(musicGenre);

        if(workId != null && workId != myAcc.getWork().getId()){
            Account firmAccount = getFirmAccountById(workId);
            account.setWork(firmAccount);
        } else {
            account.setWork(myAcc.getWork());
        }

        return repository.save(account);
    }

    @Override
    public Account getByAccId(UUID accountId) {
        Account account = repository.getAccountById(accountId);
        if(account == null){
            throw new ApiRequestException("Account doesn't exist");
        }
        return account;
    }

    @Override
    public List<Account> getByUserId(String userId) {
        List<Account> accounts = repository.getAccountsByUserId(userId);
        if(accounts.isEmpty()){
            throw new ApiRequestException("This user doesn't registered or not activated");
        }
        return accounts;
    }

    @Override
    public Account addFriend(UUID userId, UUID accountId) {
        Account user = getByAccId(userId);
        if(user == null) {
            throw new ApiRequestException("Owner account doesn't exist");
        }
        Account account = getByAccId(accountId);
        if(account == null) {
            throw new ApiRequestException("Account doesn't exist");
        }

        user.addFriend(account);
        return repository.save(user);
    }

    @Override
    public List<Account> fetchAccounts(SearchAccountsRequest parameters) {
        return null;
    }

    @Override
    public Account getFirmAccountById(UUID firmId) {
        Account firm = getByAccId(firmId);
        if(firm.getType() != AccountType.Firm){
            throw new ApiRequestException("This is not firm account");
        }
        return firm;
    }

    @Override
    public List<Account> fetchAccountsByIds(List<UUID> ids) {
        return repository.getAccountsByIds(ids);
    }

    @Override
    public List<Account> fetchAccountsWithUserSubscribe(UUID userId) {
        return getByAccId(userId).getFriends();
    }
}
