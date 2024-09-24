package com.webchat.account.service.domain.repository;

import com.webchat.account.service.domain.models.MusicGenre;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface MusicRepository extends Neo4jRepository<MusicGenre, Long> {
    @Query("MATCH (g:MusicGenre) RETURN g")
    List<MusicGenre> getAllMusicGenres();
}
