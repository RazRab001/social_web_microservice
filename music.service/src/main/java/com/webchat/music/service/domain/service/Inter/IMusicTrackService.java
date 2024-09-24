package com.webchat.music.service.domain.service.Inter;

import com.webchat.music.service.domain.model.Artist;
import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.model.MusicTrack;

import java.util.List;

public interface IMusicTrackService {
    MusicTrack addMusicTrack(MusicTrack track, List<MusicGenre> genreList, Artist artist,
                             String fileUrl, Long duration);
    MusicTrack updateMusicTrack(Long trackId, MusicTrack track, List<MusicGenre> genreList);
    List<MusicTrack> getTracksOfArtist(Long artistId);
    List<MusicTrack> getTracksOfGenre(Long genreId);
    MusicTrack getTrackById(Long trackId);
}
