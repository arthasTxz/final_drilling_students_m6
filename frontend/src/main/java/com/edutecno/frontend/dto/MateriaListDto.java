package com.edutecno.frontend.dto;

import java.util.Set;

public record MateriaListDto(
        Long id,
        String nombre,
        Set<AlumnoDTO> alumnos
) {
}
