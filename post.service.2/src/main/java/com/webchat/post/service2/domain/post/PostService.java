package com.webchat.post.service2.domain.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostService implements IPostService{
    private final PostRepository repository;
    @Override
    public void createPost(UUID creatorId, Post post) {
        post.setOwnerId(creatorId);
        repository.save(post);
    }

    @Override
    public List<Post> getPostsForAccountFeed(List<UUID> feedAccounts) {
        return repository.getPostsByOwnerIdsOrderByCreationDate(feedAccounts, 50);
    }

    @Override
    public List<Post> getPostsOfCreator(UUID creatorId) {
        return repository.getPostsByOwnerIdOrderByCreationDate(creatorId);
    }
}
