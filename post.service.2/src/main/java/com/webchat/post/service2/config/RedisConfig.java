package com.webchat.post.service2.config;

import com.webchat.post.service2.domain.post.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, List<Post>> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, List<Post>> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return template;
    }
}
