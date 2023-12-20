package com.hx.film.repo;

import com.hx.film.model.Acteur;
import com.hx.film.model.Film;
import com.hx.film.model.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActeurRepository acteurRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testGetFilmById_returnFilmDetails() {
        Film film = Film.builder()
                .titre("tigre")
                .description("documentary")
                .build();
        List<Acteur> acteurs = new ArrayList<>();
        acteurs.add(new Acteur(null, "Jack", "Mac", Gender.MAN, 30, film));
        acteurs.add(new Acteur(null, "Mamda", "Luc", Gender.WOMAN, 20, film));
//        film.setActeurs(acteurs);
//        Film savedFilm = filmRepository.save(film); //change it to TestEntityManager
        Film savedFilm = testEntityManager.persistFlushFind(film); //persistAndFlush, persistFlushFind
        System.out.println(savedFilm);
        acteurs.stream().forEach(acteur -> {acteur.setFilm(savedFilm);
            testEntityManager.persistFlushFind(acteur);});
//        System.out.println(savedFilm.getActeurs());

        Film retrieveFilm = filmRepository.findById(savedFilm.getId()).get();

        then(retrieveFilm).isNotNull();
        then(retrieveFilm.getTitre()).isEqualTo(film.getTitre());

        then(25.0).isEqualTo(acteurRepository.averageOfAge());
    }

    @Test
    void getAvgAgeForActeur() {

        Acteur Jack = new Acteur(null, "Jack", "Mac", Gender.MAN, 30, null);
        Acteur Mamda = new Acteur(null, "Mamda", "Luc", Gender.WOMAN, 20, null);
        Arrays.asList(Jack, Mamda).forEach(testEntityManager::persistFlushFind);

        Double avgAge = acteurRepository.averageOfAge();
        then(25.0).isEqualTo(avgAge);
    }
}