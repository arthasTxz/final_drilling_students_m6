package com.edutecno.frontend.dto;

import java.util.Set;

public record MateriaDTO(
        Long id,
        String nombre,
        Set<AlumnoMateriaDto> alumnos
) {
}
