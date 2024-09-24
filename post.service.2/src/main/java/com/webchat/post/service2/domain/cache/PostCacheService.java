package com.webchat.post.service2.domain.cache;

import com.webchat.post.service2.domain.post.Post;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostCacheService implements IPostsCacheService {
    private final RedisTemplate<String, List<Post>> redisTemplate;
    private final String cacheKey = "feedCache::";
    @Override
    public void addPostToFeed(UUID accountId, Post post) {
        List<Post> feedPosts = getFeedOfAccount(accountId);
        if(feedPosts == null){

        } else {
            feedPosts.add(post);
            redisTemplate.opsForValue().set(cacheKey+accountId, feedPosts);
        }
    }

    @Override
    public List<Post> getFeedOfAccount(UUID accountId) {
        return redisTemplate.opsForValue().get(cacheKey+accountId);
    }
}
