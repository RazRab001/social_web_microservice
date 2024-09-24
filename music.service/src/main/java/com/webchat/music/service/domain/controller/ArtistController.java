package com.webchat.music.service.domain.controller;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.Owner;
import com.webchat.music.service.domain.service.Inter.IArtistService;
import com.webchat.music.service.domain.service.Inter.IOwnerService;
import com.webchat.music.service.utils.request.ArtistRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/artist")
@AllArgsConstructor
public class ArtistController {
    private final IArtistService artistService;
    private final IOwnerService ownerService;

    @PostMapping("/{accountId}")
    public Artist createArtist(@PathVariable UUID accountId, @RequestBody ArtistRequest artistRequest){
        Owner accountOwner = ownerService.getOwnerById(accountId);
        return artistService.createArtist(artistRequest.toArtist(), accountOwner);
    }

    @PutMapping("/{accountId}/{artistId}")
    public Artist updateArtist(@PathVariable UUID accountId,
                               @PathVariable Long artistId,
                               @RequestBody ArtistRequest artistRequest){
        Owner accountOwner = ownerService.getOwnerById(accountId);
        Artist artist = artistService.getArtistById(artistId);
        if(artist.getOwner().getId() != accountOwner.getId()){
            throw new RuntimeException("This account can't change this artist");
        }
        return artistService.updateArtist(artistId, artistRequest.toArtist());
    }

    @GetMapping("/genre/{genreId}")
    public List<Artist> fetchArtistsOfGenre(@PathVariable Long genreId){
        return artistService.getArtistOfGenre(genreId);
    }

    @GetMapping("/owner/{accountId}")
    public List<Artist> fetchArtistsOfOwner(@PathVariable UUID accountId){
        return artistService.getArtistsOfOwnerAccount(accountId);
    }

    @GetMapping("/{artistId}")
    public Artist fetchArtist(@PathVariable Long artistId){
        return artistService.getArtistById(artistId);
    }
}
