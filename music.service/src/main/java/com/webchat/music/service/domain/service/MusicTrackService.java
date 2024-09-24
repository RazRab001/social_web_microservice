package com.webchat.music.service.domain.service;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.model.MusicTrack;
import com.webchat.music.service.domain.repository.MusicTrackRepository;
import com.webchat.music.service.domain.service.Inter.IMusicTrackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MusicTrackService implements IMusicTrackService {

    private final MusicTrackRepository repository;
    @Override
    public MusicTrack addMusicTrack(MusicTrack track, List<MusicGenre> genreList,
                                    Artist artist, String fileUrl, Long duration) {
        track.setGenres(genreList);
        track.setArtist(artist);
        track.setFilePath(fileUrl);
        track.setDurationSeconds(duration);

        return repository.save(track);
    }

    @Override
    public MusicTrack updateMusicTrack(Long trackId, MusicTrack track, List<MusicGenre> genreList) {
        MusicTrack my_track = getTrackById(trackId);

        track.setId(trackId);
        track.setGenres(genreList);
        track.setArtist(my_track.getArtist());
        track.setFilePath(my_track.getFilePath());
        track.setDurationSeconds(my_track.getDurationSeconds());

        return repository.save(track);
    }

    @Override
    public List<MusicTrack> getTracksOfArtist(Long artistId) {
        return repository.getMusicTracksByArtist_Id(artistId);
    }

    @Override
    public List<MusicTrack> getTracksOfGenre(Long genreId) {
        return repository.getMusicTracksByGenreId(genreId);
    }

    @Override
    public MusicTrack getTrackById(Long trackId) {
        return repository.getMusicTrackById(trackId);
    }
}
