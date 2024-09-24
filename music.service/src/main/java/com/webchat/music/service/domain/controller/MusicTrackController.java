package com.webchat.music.service.domain.controller;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.model.MusicTrack;
import com.webchat.music.service.domain.service.Inter.*;
import com.webchat.music.service.utils.request.TrackRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/track")
@AllArgsConstructor
public class MusicTrackController {
    private final IAudioService audioService;
    private final IMusicTrackService musicTrackService;
    private final IMusicGenreService genreService;
    private final IArtistService artistService;
    private final ICloudService cloudService;

    @PostMapping("/{artistId}")
    public MusicTrack addTrack(@PathVariable Long artistId,
                               @RequestPart String title,
                               @RequestPart String album,
                               @RequestParam("genres") List<String> genres,
                               //@RequestPart TrackRequest trackRequest,
                               @RequestPart("audio")  MultipartFile audio) throws IOException {
        Artist artist = artistService.getArtistById(artistId);
        List<MusicGenre> genreList = genreService.checkAndUpdateMusicGenres(genres);
        artistService.updateArtistsGenres(artist, genreList);

        String filePath = cloudService.uploadFile(audio);
        Long duration = audioService.getDuration(audio);

        return musicTrackService.addMusicTrack(new MusicTrack(title, album), genreList,
                artist, filePath, duration);
    }

    @PutMapping("/{trackId}")
    public MusicTrack updateTrack(@PathVariable Long trackId,
                                  @RequestBody TrackRequest trackRequest){
        List<MusicGenre> genreList = genreService.checkAndUpdateMusicGenres(trackRequest.getGenres());
        return musicTrackService.updateMusicTrack(trackId, trackRequest.toMusicTrack(), genreList);
    }

    @GetMapping("/download/{trackId}")
    public ResponseEntity<byte[]> downloadTrack(@PathVariable Long trackId) {
        try{
            MusicTrack track = musicTrackService.getTrackById(trackId);
            String fileName = track.getFileName();

            byte[] fileContent = cloudService.downloadFile(fileName);
            String contentType = cloudService.getContentType(fileName);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/{trackId}")
    public MusicTrack getTrackById(@PathVariable Long trackId){
        return musicTrackService.getTrackById(trackId);
    }

    @GetMapping("/artist/{artistId}")
    public List<MusicTrack> getTracksOfArtist(@PathVariable Long artistId){
        return musicTrackService.getTracksOfArtist(artistId);
    }

    @GetMapping("/genre/{genreId}")
    public List<MusicTrack> getTracksOfGenre(@PathVariable Long genreId){
        return musicTrackService.getTracksOfGenre(genreId);
    }
}
