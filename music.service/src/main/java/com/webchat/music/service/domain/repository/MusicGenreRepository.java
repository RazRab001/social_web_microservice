package com.webchat.music.service.domain.repository;

import com.webchat.music.service.domain.model.MusicGenre;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MusicGenreRepository extends Neo4jRepository<MusicGenre, Long> {
    MusicGenre getMusicGenreById(Long id);
    MusicGenre getMusicGenreByName(String name);
}
