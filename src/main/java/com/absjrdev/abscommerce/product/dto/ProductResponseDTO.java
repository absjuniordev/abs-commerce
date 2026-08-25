package com.absjrdev.abscommerce.product.dto;

import com.absjrdev.abscommerce.product.domain.Product;
import com.absjrdev.abscommerce.category.dto.CategoryResponseDTO;

import java.util.Set;
import java.util.stream.Collectors;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        Double price,
        String imgUrl,
        Set<CategoryResponseDTO> categories
) {

    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImgUrl(),
                product.getCategories()
                        .stream()
                        .map(CategoryResponseDTO::new)
                        .collect(Collectors.toSet())
        );
    }
}