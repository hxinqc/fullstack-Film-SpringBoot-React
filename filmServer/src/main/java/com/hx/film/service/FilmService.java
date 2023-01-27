package com.hx.film.service;

import com.hx.film.model.Acteur;
import com.hx.film.model.Film;
import com.hx.film.repo.ActeurRepository;
import com.hx.film.repo.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FilmService {
    private FilmRepository filmRepository;
    private ActeurRepository acteurRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, ActeurRepository acteurRepository) {
        this.filmRepository = filmRepository;
        this.acteurRepository = acteurRepository;
    }

    @Transactional
    public Film createFilm(Film film) {
        Film newFilm = Film.builder()
                .titre(film.getTitre())
                .acteurs(film.getActeurs())
                .description(film.getDescription())
                .build();
        List<Acteur> acteurs = film.getActeurs();
        acteurs.forEach(acteur -> acteur.setFilm(newFilm));
        newFilm.setActeurs(acteurs);
        return filmRepository.save(newFilm);
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Acteur getActeurById(Long id) {
        return acteurRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
