package com.conexa.challenge.controller;

import com.conexa.challenge.model.Film;
import com.conexa.challenge.model.SWApiResponse;
import com.conexa.challenge.model.SWApiResponseList;
import com.conexa.challenge.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<SWApiResponseList<Film>> getPagedFilms() {
        return ResponseEntity.ok(filmService.getPagedFilms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SWApiResponse<Film>> getFilmById(@PathVariable int id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }
}
