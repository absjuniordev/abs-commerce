package com.absjrdev.abscommerce.order.dto;

import com.absjrdev.abscommerce.order.domain.Order;
import com.absjrdev.abscommerce.order.domain.orderStatus.OrderStatus;

import java.time.Instant;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private Instant moment;
    private OrderStatus orderStatus;
    private Long clientId;
    private List<OrderItemResponseDTO> items;
    private Double total;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.moment = order.getMoment();
        this.orderStatus = order.getOrderStatus();
        this.clientId = order.getClient().getId();
        this.items = order.getItems()
                .stream()
                .map(OrderItemResponseDTO::new)
                .toList();
        this.total = order.getTotal();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Long getClientId() {
        return clientId;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }
}