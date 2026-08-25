package com.absjrdev.abscommerce.category.dto;

import com.absjrdev.abscommerce.category.domain.Category;

public record CategoryResponseDTO(
        Long id,
        String name
) {

    public CategoryResponseDTO(Category category) {
        this(
                category.getId(),
                category.getName()
        );
    }
}