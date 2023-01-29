package com.hx.film.service;

import com.hx.film.model.Acteur;
import com.hx.film.model.Film;
import com.hx.film.model.Gender;
import com.hx.film.repo.FilmRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.catchException;
import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class FilmServiceTest {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmService filmService;
    @DisplayName("Returning saved film from service layer")
    @Test
    void getFilmById_forSavedFilm_isReturned() {
        Film film = Film.builder()
                .titre("tigre")
                .description("documentary")
                .build();
        List<Acteur> acteurs = new ArrayList<>();
        acteurs.add(new Acteur(null, "Jack", "Mac", Gender.MAN, 30, film));
        acteurs.add(new Acteur(null, "Mamda", "Luc", Gender.WOMAN, 20, film));
        film.setActeurs(acteurs);
        Film savedFilm = filmRepository.save(film);

        Film retrieveFilm = filmService.getFilmById(film.getId());

        then(savedFilm.getTitre()).isEqualTo(retrieveFilm.getTitre());

    }
    @DisplayName("Test exception")
    @Test
    void getFilmById_whenMissingFilm_notFoundExceptionThrown() {
        Long id = 1234L;

        Throwable throwable = catchException(() -> filmService.getFilmById(id));

        BDDAssertions.then(throwable).isInstanceOf(FilmNotFoundException.class);
    }
}