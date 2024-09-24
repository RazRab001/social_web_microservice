package com.webchat.music.service.domain.service;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.model.Owner;
import com.webchat.music.service.domain.repository.ArtistRepository;
import com.webchat.music.service.domain.service.Inter.IArtistService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistService implements IArtistService {
    private final ArtistRepository repository;
    @Override
    public Artist createArtist(Artist artist, Owner artistAccountOwner) {
        artist.setOwner(artistAccountOwner);
        return repository.save(artist);
    }

    @Override
    public Artist updateArtist(Long artistId, Artist artist) {
        Artist existArtist = getArtistById(artistId);
        artist.setId(artistId);
        artist.setOwner(existArtist.getOwner());
        artist.setTrackList(existArtist.getTrackList());
        return repository.save(artist);
    }

    @Override
    public List<Artist> getArtistOfGenre(Long genreId) {
        return repository.getArtistsByGenreId(genreId);
    }

    @Override
    public List<Artist> getArtistsOfOwnerAccount(UUID accountId) {
        return repository.getArtistsByOwner_Id(accountId);
    }

    @Override
    public Artist getArtistById(Long artistId) {
        Artist artist = repository.getArtistById(artistId);
        if(artist == null){
            throw new RuntimeException("Artist doesn't exist");
        }
        return artist;
    }

    @Override
    public void updateArtistsGenres(Artist artist, List<MusicGenre> genreList) {
        for(MusicGenre genre : genreList){
            if(!artist.getGenreList().contains(genre)){
                artist.addGenreInList(genre);
            }
        }
        repository.save(artist);
    }

}
