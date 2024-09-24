package com.webchat.music.service.domain.service.Inter;

import com.webchat.music.service.domain.model.MusicGenre;

import java.util.List;

public interface IMusicGenreService {
    List<MusicGenre> getAllGenres();
    MusicGenre createGenre(MusicGenre genre);
    MusicGenre getGenreById(Long id);
    MusicGenre getGenreByName(String name);
    List<MusicGenre> checkAndUpdateMusicGenres(List<String> genreIds);
}
