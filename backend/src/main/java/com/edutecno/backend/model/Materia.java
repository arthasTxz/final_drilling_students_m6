package com.edutecno.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;


    @ManyToMany
    @JoinTable(
            name = "materia_alumno",
            joinColumns = @JoinColumn(name = "materias_id"),
            inverseJoinColumns = @JoinColumn(name = "alumnos_id")
    )
    private Set<Alumno> alumnos;

}
