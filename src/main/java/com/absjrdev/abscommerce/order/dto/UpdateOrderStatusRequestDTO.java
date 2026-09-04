package com.absjrdev.abscommerce.order.dto;

import com.absjrdev.abscommerce.order.domain.orderStatus.OrderStatus;

public record UpdateOrderStatusRequestDTO(
        OrderStatus status
) {
}
