package com.webchat.music.service.domain.repository;

import com.webchat.music.service.domain.model.Artist;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ArtistRepository extends Neo4jRepository<Artist, Long> {
    Artist getArtistById(Long id);
    List<Artist> getArtistsByOwner_Id(UUID ownerId);
    @Query("MATCH (a:Artist)-[:PLAY_IN_GENRE]->(g:MusicGenre) WHERE id(g) = $genreId RETURN a")
    List<Artist> getArtistsByGenreId(@Param("genreId") Long genreId);

    @Query("MATCH (a:Artist)-[:OWN_BY]->(o:Owner) WHERE id(o) = $ownerId RETURN a")
    List<Artist> getArtistsByAccountOwnerId(@Param("ownerId") Long ownerId);
}
