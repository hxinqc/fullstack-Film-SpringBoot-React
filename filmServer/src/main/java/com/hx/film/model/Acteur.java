package com.hx.film.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "acteurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acteur_seq")
    @SequenceGenerator(name = "acteur_seq", allocationSize = 1)
    @Column(name = "acteur_id")
    private Long id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    @Enumerated
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonIgnore
    private Film film;

}
