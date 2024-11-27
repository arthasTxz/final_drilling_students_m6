package com.edutecno.frontend.dto;

import java.util.Set;

public record AlumnoResponseDto(
        Long id,
        String nombre,
        String direccion,
        Set<MateriaDTO> materias
) {
}
