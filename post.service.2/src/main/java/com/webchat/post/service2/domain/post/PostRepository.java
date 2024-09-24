package com.webchat.post.service2.domain.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface PostRepository extends MongoRepository<Post, UUID> {

    @Query("{ 'ownerId': { $in: ?0 } }")
    List<Post> findByOwnerIds(List<UUID> ownerIds, Sort sort);
    List<Post> getPostsByOwnerIdOrderByCreationDate(UUID ownerId);
    default List<Post> getPostsByOwnerIdsOrderByCreationDate(List<UUID> ownerIds, Integer limit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "creationDate");
        return findByOwnerIds(ownerIds, sort).stream().limit(limit).toList();
    }
}
