package com.webchat.music.service.utils.request;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.model.MusicTrack;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
@Getter
public class TrackRequest {
    private String title;
    private String album;
    private List<String> genres;

    public MusicTrack toMusicTrack(){
        return new MusicTrack(
                this.title, this.album);
    }
}
