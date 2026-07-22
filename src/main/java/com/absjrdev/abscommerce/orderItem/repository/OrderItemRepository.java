package com.absjrdev.abscommerce.orderItem.repository;

import com.absjrdev.abscommerce.orderItem.domain.OrderItem;
import com.absjrdev.abscommerce.orderItem.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

    Optional<OrderItem> findById(OrderItemPK id);
}