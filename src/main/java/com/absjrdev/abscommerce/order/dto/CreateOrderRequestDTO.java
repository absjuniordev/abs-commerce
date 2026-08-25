package com.absjrdev.abscommerce.order.dto;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequestDTO {

    private Long clientId;

    private List<OrderItemRequestDTO> items = new ArrayList<>();

    public CreateOrderRequestDTO() {
    }

    public CreateOrderRequestDTO(Long clientId, List<OrderItemRequestDTO> items) {
        this.clientId = clientId;
        this.items = items;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}