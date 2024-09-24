package com.webchat.music.service.domain.repository;

import com.webchat.music.service.domain.model.Owner;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface OwnerRepository extends Neo4jRepository<Owner, UUID> {
    Owner getOwnerById(UUID id);
}
