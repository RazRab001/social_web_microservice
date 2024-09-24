package com.webchat.account.service.domain.controller;

import com.webchat.account.service.domain.models.Account;
import com.webchat.account.service.domain.models.MusicGenre;
import com.webchat.account.service.domain.service.inter.IAccountService;
import com.webchat.account.service.domain.service.inter.IMusicGenreService;
import com.webchat.account.service.utils.request.AccountRequest;
import com.webchat.account.service.utils.request.SearchAccountsRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {
    private final IAccountService accountService;
    private final IMusicGenreService musicGenreService;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@PathVariable String userId){
        return accountService.create(userId);
    }

    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable UUID accountId, @RequestBody AccountRequest accountRequest){
        List<MusicGenre> result = new ArrayList<>();
        List<MusicGenre> musicGenreList = musicGenreService.getAllMusicGenres();
        List<String> musicGenreRequest = accountRequest.getMusicGenre();

        if(musicGenreRequest != null){
            Map<Long, MusicGenre> musicGenreMap = new HashMap<>();
            for (MusicGenre musicGenre : musicGenreList) {
                musicGenreMap.put(musicGenre.getId(), musicGenre);
            }

            for (String genre : musicGenreRequest) {
                try {
                    Long genreId = Long.parseLong(genre);
                    MusicGenre musicGenre = musicGenreMap.get(genreId);
                    if (musicGenre != null) {
                        result.add(musicGenre);
                    }
                } catch (NumberFormatException e) {
                    MusicGenre newGenre = musicGenreService.addNewMusicGenre(genre);
                    result.add(newGenre);
                }
            }
        }

        return accountService.update(accountId, accountRequest.toAccount(),
                result, accountRequest.getWork());
    }

    @GetMapping("/{userId}")
    public List<Account> getAccountsByUserId(@PathVariable String userId){
        return accountService.getByUserId(userId);
    }

    @PostMapping("/{userId}/{accountId}")
    public Account addFriend(@PathVariable UUID userId, @PathVariable UUID accountId){
        return accountService.addFriend(userId, accountId);
    }

    @GetMapping("")
    public List<Account> fetchAccounts(@RequestBody SearchAccountsRequest parameters){
        return accountService.fetchAccounts(parameters);
    }

    @PutMapping("/match")
    public List<Account> fetchAccountsByIds(@RequestBody List<UUID> uuids){
        return accountService.fetchAccountsByIds(uuids);
    }

    @GetMapping("/subs/{accountId}")
    public List<UUID> getIdsOfAccountsWithUserSubscribe(@PathVariable UUID accountId){
        return accountService.fetchAccountsWithUserSubscribe(accountId)
                .stream()
                .map(Account::getId)
                .collect(Collectors.toList());
    }
}
