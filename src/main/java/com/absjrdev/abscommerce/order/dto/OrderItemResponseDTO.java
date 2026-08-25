package com.absjrdev.abscommerce.order.dto;

import com.absjrdev.abscommerce.orderItem.domain.OrderItem;

public class OrderItemResponseDTO {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double subTotal;

    public OrderItemResponseDTO() {
    }

    public OrderItemResponseDTO(OrderItem item) {
        this.productId = item.getProduct().getId();
        this.productName = item.getProduct().getName();
        this.quantity = item.getQuantity();
        this.price = item.getPrice();
        this.subTotal = item.getSubTotal();
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSubTotal() {
        return subTotal;
    }
}