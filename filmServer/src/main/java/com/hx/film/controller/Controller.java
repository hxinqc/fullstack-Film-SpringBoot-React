package com.hx.film.controller;

import com.hx.film.model.Acteur;
import com.hx.film.model.Film;
import com.hx.film.model.FilmDisplay;
import com.hx.film.service.FilmNotFoundException;
import com.hx.film.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    @Autowired
    private final FilmService filmService;

    @PostMapping("/api/film")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDisplay createFilm(@RequestBody FilmDisplay filmDisplay) {
        return filmService.createFilm(filmDisplay);
    }

    @GetMapping("/api/film")
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/api/film/{id}")
    public Film getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/api/acteur/{id}")
    public Acteur getActeurById(@PathVariable Long id) {
        return filmService.getActeurById(id);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void filmNotFoundException(FilmNotFoundException e) {

    }
}
