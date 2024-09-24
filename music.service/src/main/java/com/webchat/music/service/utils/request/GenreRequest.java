package com.webchat.music.service.utils.request;

import com.webchat.music.service.domain.model.MusicGenre;
import lombok.Getter;

@Getter
public class GenreRequest {
    private String name;

    public MusicGenre toGenre(){
        return new MusicGenre(this.name);
    }
}
