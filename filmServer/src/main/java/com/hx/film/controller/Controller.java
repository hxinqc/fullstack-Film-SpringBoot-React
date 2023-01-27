package com.hx.film.controller;

import com.hx.film.model.Acteur;
import com.hx.film.model.Film;
import com.hx.film.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private FilmService filmService;

    @PostMapping("/api/film")
    @ResponseStatus(HttpStatus.CREATED)
    public Film createFilm(@RequestBody Film film) {
        return filmService.createFilm(film);
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
}
