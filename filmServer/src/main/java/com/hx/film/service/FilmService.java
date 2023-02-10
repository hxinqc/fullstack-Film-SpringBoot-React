package com.hx.film.service;

import com.hx.film.model.Acteur;
import com.hx.film.model.Film;
import com.hx.film.model.FilmDisplay;
import com.hx.film.repo.ActeurRepository;
import com.hx.film.repo.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public FilmDisplay createFilm(FilmDisplay filmDisplay) {
        Film newFilm = Film.builder()
                .titre(filmDisplay.getTitre())
                .description(filmDisplay.getDescription())
                .build();
        List<Acteur> acteurs = filmDisplay.getActeurs();
        Film finalNewFilm = filmRepository.save(newFilm);

        acteurs.forEach(acteur -> { acteur.setFilm(finalNewFilm);
            acteurRepository.save(acteur);});
        filmDisplay.setId(finalNewFilm.getId());
        filmDisplay.setActeurs(acteurs);
        return filmDisplay;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Cacheable("films")
    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException());
    }

    public Acteur getActeurById(Long id) {
        return acteurRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
}
