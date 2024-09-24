package com.webchat.account.service.domain.repository;

import com.webchat.account.service.domain.models.Account;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends Neo4jRepository<Account, UUID> {
    List<Account> getAccountsByUserId(String userId);
    @Query("MATCH (a:Account) WHERE a.id IN $ids RETURN a")
    List<Account> getAccountsByIds(List<UUID> ids);
    Account getAccountById(UUID id);
    @Query("MATCH (a:Account) " +
            "OPTIONAL MATCH (a)-[:GENDER]->(g:Gender) " +
            "OPTIONAL MATCH (a)-[:LIKE_MUSIC]->(m:MusicGenre) " +
            "OPTIONAL MATCH (a)-[:FRIEND]->(f:Account) " +
            "WITH a, g, m, f " +
            "WHERE " +
            "($name IS NULL OR a.name = $name) AND " +
            "($surname IS NULL OR a.surname = $surname) AND " +
            "($gender IS NULL OR id(g) = $gender) AND " +
            "($musicGenre IS NULL OR id(m) = $musicGenre) AND " +
            "($friend IS NULL OR id(f) = $friend) " +
            "RETURN DISTINCT a")
    List<Account> findByMultipleCriteria(
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("gender") Long gender,
            @Param("musicGenre") Long musicGenre,
            @Param("friend") UUID friend);
}
