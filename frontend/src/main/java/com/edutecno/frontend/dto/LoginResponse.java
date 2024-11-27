package com.edutecno.frontend.dto;

public record LoginResponse(
        Boolean success,
        String token,
        String message
) {
}
