package com.absjrdev.abscommerce.product.dto;

import com.absjrdev.abscommerce.category.dto.CategoryResponseDTO;

import java.util.Set;

public record ProductRequestDTO(
        String name,
        String description,
        Double price,
        String imgUrl,
        Set<CategoryResponseDTO> categories
) {
}
