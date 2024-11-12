package com.edutecno.backend.dto;

public record AlumnoCreateRequest(
        String rut,
        String nombre,
        String direccion) {
}
