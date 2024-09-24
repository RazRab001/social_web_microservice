package com.webchat.music.service.utils.request;

import com.webchat.music.service.domain.model.Artist;
import lombok.Getter;

@Getter
public class ArtistRequest {
    private String title;
    public Artist toArtist(){
        return new Artist(this.title);
    }
}
