package com.edutecno.frontend.dto;

public record MateriaDTO(
        Long id,
        String nombre,
        AlumnoDTO alumno
) {
}
