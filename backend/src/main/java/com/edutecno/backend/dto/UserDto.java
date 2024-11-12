package com.edutecno.backend.dto;

import com.edutecno.backend.model.Role;

import java.util.List;

public record UserDto(
        String username,
        String password,
        List<Role> roles
) {
}
