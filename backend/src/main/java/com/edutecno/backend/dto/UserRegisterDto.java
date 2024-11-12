package com.edutecno.backend.dto;

public record UserRegisterDto(
        String name,
        String username,
        String password,
        String email
) {
}
