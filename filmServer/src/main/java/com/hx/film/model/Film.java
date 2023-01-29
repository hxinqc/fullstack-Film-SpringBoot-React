package com.hx.film.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "film")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "film_seq")
    @SequenceGenerator(name = "film_seq", allocationSize = 1)
    private Long id;
    @Column
    private String titre;
    @Column(length = 1000)
    private String description;
    @OneToMany(mappedBy = "film", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Acteur> acteurs;

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
