package com.webchat.post.service2.domain.post;

import java.util.List;
import java.util.UUID;

public interface IPostService {
    void createPost(UUID creatorId, Post post);
    List<Post> getPostsForAccountFeed(List<UUID> feedAccounts);
    List<Post> getPostsOfCreator(UUID creatorId);
}
