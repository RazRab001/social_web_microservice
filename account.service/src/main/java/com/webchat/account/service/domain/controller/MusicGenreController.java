package com.webchat.account.service.domain.controller;

import com.webchat.account.service.domain.models.MusicGenre;
import com.webchat.account.service.domain.service.MusicGenreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music")
@AllArgsConstructor
public class MusicGenreController {
    private final MusicGenreService musicService;

    @GetMapping("")
    public List<MusicGenre> getMusicGenres(){
        return musicService.getAllMusicGenres();
    }
}
