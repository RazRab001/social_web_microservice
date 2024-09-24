package com.webchat.music.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

@Node("Artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "OWN_BY")
    private Owner owner;

    private String title;

    @Relationship(type = "PLAY_TRACK")
    private List<MusicTrack> trackList = emptyList();

    @Relationship(type = "PLAY_IN_GENRE")
    private Set<MusicGenre> genreList = emptySet();

    public Artist(String title) {
        this.title = title;
    }

    public void addGenreInList(MusicGenre genre){
        if(!this.getGenreList().contains(genre)){
            this.genreList.add(genre);
        }
    }
}
