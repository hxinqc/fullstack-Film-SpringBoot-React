package com.hx.film.service;

import com.hx.film.model.Film;
import com.hx.film.repo.FilmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class FilmCacheTest {
    @Autowired
    private FilmService filmService;
    @MockBean
    private FilmRepository filmRepository;
    @Test
    void getFilmById_forMultipleRequests_isRetrievedFromCache() {

        Long id = 123L;
        given(filmRepository.findById(id)).willReturn(Optional.of(new Film(id, "titre", "description")));

        filmService.getFilmById(id);
        filmService.getFilmById(id);
        filmService.getFilmById(id);

        then(filmRepository).should(times(1)).findById(id);
    }
}
