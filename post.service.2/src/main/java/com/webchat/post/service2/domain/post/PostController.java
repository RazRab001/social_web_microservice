package com.webchat.post.service2.domain.post;

import com.webchat.post.service2.domain.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
public class PostController {
    private final IPostService postService;
    private final IAccountService accountService;

    @PostMapping("/{creatorId}")
    public void addNewPost(@PathVariable UUID creatorId, @RequestBody Post post){
        postService.createPost(creatorId, post);
    }

    @GetMapping("/{accountId}")
    public List<Post> getPostForAccountFeed(@PathVariable UUID accountId){
        List<UUID> feedIds = accountService.getIdsOfFriendAccounts(accountId);
        return postService.getPostsForAccountFeed(feedIds);
    }

    @GetMapping("/acc/{accountId}")
    public List<Post> getPostOfCreator(@PathVariable UUID accountId){
        return postService.getPostsOfCreator(accountId);
    }
}
