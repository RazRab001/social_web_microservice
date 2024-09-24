package com.webchat.music.service.domain.service;

import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.repository.MusicGenreRepository;
import com.webchat.music.service.domain.service.Inter.IMusicGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MusicGenreService implements IMusicGenreService {
    private final MusicGenreRepository repository;
    @Override
    public List<MusicGenre> getAllGenres() {
        return repository.findAll();
    }

    @Override
    public MusicGenre createGenre(MusicGenre genre) {
        return repository.save(genre);
    }

    @Override
    public MusicGenre getGenreById(Long id) {
        return repository.getMusicGenreById(id);
    }

    @Override
    public MusicGenre getGenreByName(String name) {
        return repository.getMusicGenreByName(name);
    }

    @Override
    public List<MusicGenre> checkAndUpdateMusicGenres(List<String> genreIds){
        List<MusicGenre> result = new ArrayList<>();
        List<MusicGenre> musicGenreList = getAllGenres();

        Map<Long, MusicGenre> musicGenreMap = new HashMap<>();
        for (MusicGenre musicGenre : musicGenreList) {
            musicGenreMap.put(musicGenre.getId(), musicGenre);
        }

        for (String genre : genreIds) {
            try {
                Long genreId = Long.parseLong(genre);
                MusicGenre musicGenre = musicGenreMap.get(genreId);
                if (musicGenre != null) {
                    result.add(musicGenre);
                }
            } catch (NumberFormatException e) {
                MusicGenre newGenre = createGenre(new MusicGenre(genre));
                result.add(newGenre);
            }
        }
        return result;
    }
}
