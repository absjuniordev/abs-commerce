package com.absjrdev.abscommerce.product.dto;

public record ProductRequestDTO(
        String name,
        String description,
        Double price,
        String imgUrl
) {
}
