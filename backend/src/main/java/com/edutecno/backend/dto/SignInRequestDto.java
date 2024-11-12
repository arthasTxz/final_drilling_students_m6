package com.edutecno.backend.dto;

public record SignInRequestDto(
        String username,
        String password
) {
}
