package com.edutecno.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Table(name = "alumnos")
@Data
@NoArgsConstructor
@Entity
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rut;

    private String nombre;

    private String direccion;

    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    private Set<Materia> materiaList = new HashSet<>();
}
