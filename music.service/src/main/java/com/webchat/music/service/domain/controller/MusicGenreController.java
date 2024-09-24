package com.webchat.music.service.domain.controller;

import com.webchat.music.service.domain.model.MusicGenre;
import com.webchat.music.service.domain.service.Inter.IMusicGenreService;
import com.webchat.music.service.utils.request.GenreRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
@AllArgsConstructor
public class MusicGenreController {
    private final IMusicGenreService genreService;

    @GetMapping("")
    public List<MusicGenre> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping("")
    public MusicGenre createGenre(@RequestBody GenreRequest genreRequest) {
        return genreService.createGenre(genreRequest.toGenre());
    }

    @GetMapping("/{genreId}")
    public MusicGenre getGenreById(@PathVariable Long genreId) {
        return genreService.getGenreById(genreId);
    }

    @GetMapping("/search/{genre}")
    public MusicGenre getGenreByName(@PathVariable String genre) {
        return genreService.getGenreByName(genre);
    }
}
