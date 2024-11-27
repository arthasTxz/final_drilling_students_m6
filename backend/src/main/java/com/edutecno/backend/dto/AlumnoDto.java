package com.edutecno.backend.dto;

import com.edutecno.backend.model.Materia;

import java.util.List;
import java.util.Set;

public record AlumnoDto(
        Long id,
        String nombre,
        String direccion,
        Set<Materia> materias
) {
}
