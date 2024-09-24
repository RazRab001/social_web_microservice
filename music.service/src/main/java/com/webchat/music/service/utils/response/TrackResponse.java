package com.webchat.music.service.utils.response;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

import static java.util.Collections.emptyList;

@Getter
@AllArgsConstructor
public class TrackResponse {
    private Long id;
    private String title;
    private Artist artist;
    private String album;
    private Long durationSeconds;
    private List<MusicGenre> genres;
}
