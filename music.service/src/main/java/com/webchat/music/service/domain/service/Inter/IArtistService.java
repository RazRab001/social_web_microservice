package com.webchat.music.service.domain.service.Inter;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.model.Owner;

import java.util.List;
import java.util.UUID;

public interface IArtistService {
    Artist createArtist(Artist artist, Owner artistAccountOwner);
    Artist updateArtist(Long artistId, Artist artist);
    List<Artist> getArtistOfGenre(Long genreId);
    List<Artist> getArtistsOfOwnerAccount(UUID accountId);
    Artist getArtistById(Long artistId);
    void updateArtistsGenres(Artist artist, List<MusicGenre> genreList);
}
