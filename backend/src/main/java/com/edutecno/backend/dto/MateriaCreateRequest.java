package com.edutecno.backend.dto;


public record MateriaCreateRequest(
        String nombre,
        Long alumnoId
) {
}
