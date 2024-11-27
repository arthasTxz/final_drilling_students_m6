package com.edutecno.backend.dto;

import com.edutecno.backend.model.Materia;

import java.util.List;

public record MateriasByAlumno(
        List<Materia> materias
) {
}
