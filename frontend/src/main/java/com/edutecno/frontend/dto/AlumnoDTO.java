package com.edutecno.frontend.dto;

import java.util.Set;

public record AlumnoDTO(
        Long id,
        String rut,
        String nombre,
        String direccion,
        Set<MateriaDTO> materiaList
) {
}
