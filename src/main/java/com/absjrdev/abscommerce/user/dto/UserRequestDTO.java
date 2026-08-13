package com.absjrdev.abscommerce.user.dto;

public record UserRequestDTO(
        String name,
        String email,
        String phone,
        String password
) {
}
