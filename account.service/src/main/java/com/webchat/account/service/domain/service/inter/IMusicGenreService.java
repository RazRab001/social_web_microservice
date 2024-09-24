package com.webchat.account.service.domain.service.inter;

import com.webchat.account.service.domain.models.MusicGenre;

import java.util.List;

public interface IMusicGenreService {
    List<MusicGenre> getAllMusicGenres();
    MusicGenre  addNewMusicGenre(String genre);
}
