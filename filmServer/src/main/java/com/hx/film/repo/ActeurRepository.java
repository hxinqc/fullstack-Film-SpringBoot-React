package com.hx.film.repo;

import com.hx.film.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {
    @Query("SELECT AVG(e.age) FROM Acteur e ") //JPQL
    Double averageOfAge();
}
