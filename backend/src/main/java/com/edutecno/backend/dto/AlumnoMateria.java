package com.edutecno.backend.dto;

import com.edutecno.backend.model.Alumno;

import java.util.List;

public record AlumnoMateria(
        List<Alumno> alumnos
) {
}
