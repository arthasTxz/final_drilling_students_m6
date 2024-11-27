package com.edutecno.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumnos")
@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @JsonIgnore
    private String rut;

    private String nombre;

    private String direccion;

    @ManyToMany(mappedBy = "alumnos")
    @JsonIgnore
    private Set<Materia> materiaList;

}
