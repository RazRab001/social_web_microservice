package com.webchat.post.service2.domain.cache;

import com.webchat.post.service2.domain.post.Post;

import java.util.List;
import java.util.UUID;

public interface IPostsCacheService {
    void addPostToFeed(UUID accountId, Post post);
    List<Post> getFeedOfAccount(UUID accountId);
}
