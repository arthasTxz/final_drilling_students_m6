package com.edutecno.frontend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMateriaToAlumno{

    private Long alumnoId;
    private Long materiaId;

    @Override
    public String toString() {
        return "AddMateriaToAlumno{" +
                "alumnoId=" + alumnoId +
                ", materiaId=" + materiaId +
                '}';
    }
}
