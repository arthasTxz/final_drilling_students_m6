package com.edutecno.backend.dto;

import com.edutecno.backend.model.Materia;

import java.util.List;

public record MateriaWrapper(
        List<Materia> materias
) {
}
