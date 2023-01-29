package com.hx.film.controller;

import com.hx.film.model.Film;
import com.hx.film.service.FilmNotFoundException;
import com.hx.film.service.FilmService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @Test
    void getFilm_forSavedFilm_isReturned() throws Exception {
        given(filmService.getFilmById(anyLong())).willReturn(
                Film.builder()
                        .id(1l)
                        .titre("Jack")
                        .description("description")
                        .build()
        );

        mockMvc.perform(get("/api/film/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("titre").value("Jack"))
                .andExpect(jsonPath("description").value("description"));
    }

    @Test
    void getFilm_forMissingFilm_status404() throws Exception {
        given(filmService.getFilmById(anyLong())).willThrow(FilmNotFoundException.class);

        mockMvc.perform(get("/api/film/1"))
                .andExpect(status().isNotFound());
    }

}