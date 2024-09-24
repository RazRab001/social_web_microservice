package com.webchat.music.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

import static java.util.Collections.emptyList;

@Node("MusicTrack")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicTrack {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Relationship(type = "PLAY_BY")
    private Artist artist;

    private String album;
    private Long durationSeconds;

    private String filePath;

    @Relationship(type = "HAS_GENRE")
    private List<MusicGenre> genres = emptyList();

    public MusicTrack(String title, String album){
        this.title = title;
        this.album = album;
    }

    public String getFileName(){
        int lastSlashIndex = filePath.lastIndexOf("/");
        if (lastSlashIndex != -1) {
            return filePath.substring(lastSlashIndex + 1);
        } else {
            return filePath;
        }
    }
}

