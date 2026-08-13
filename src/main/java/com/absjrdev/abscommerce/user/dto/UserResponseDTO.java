package com.absjrdev.abscommerce.user.dto;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phone
) {
}
