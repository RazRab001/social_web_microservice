package com.webchat.music.service.domain.repository;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicTrack;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicTrackRepository extends Neo4jRepository<MusicTrack, Long> {
    MusicTrack getMusicTrackById(Long id);
    List<MusicTrack> getMusicTracksByArtist_Id(Long artistId);
    @Query("MATCH (t:MusicTrack)-[:HAS_GENRE]->(g:MusicGenre) WHERE id(g) = $genreId RETURN a")
    List<MusicTrack> getMusicTracksByGenreId(@Param("genreId") Long genreId);
}

