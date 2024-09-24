package com.webchat.account.service.domain.service;

import com.webchat.account.service.domain.models.MusicGenre;
import com.webchat.account.service.domain.repository.MusicRepository;
import com.webchat.account.service.domain.service.inter.IMusicGenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MusicGenreService implements IMusicGenreService {
    private final MusicRepository repository;
    @Override
    public List<MusicGenre> getAllMusicGenres() {
        return repository.getAllMusicGenres();
    }

    public MusicGenre addNewMusicGenre(String genre){
        //MusicGenre musicGenre = new MusicGenre(genre);
        return repository.save(new MusicGenre(genre));
        //return repository.getAllMusicGenres();
    }
}
