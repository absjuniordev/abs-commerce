package com.absjrdev.abscommerce.order.application;

import com.absjrdev.abscommerce.exception.BusinessException;
import com.absjrdev.abscommerce.exception.ResourceNotFoundException;
import com.absjrdev.abscommerce.order.domain.Order;
import com.absjrdev.abscommerce.order.domain.orderStatus.OrderStatus;
import com.absjrdev.abscommerce.order.dto.CreateOrderRequestDTO;
import com.absjrdev.abscommerce.order.dto.OrderItemRequestDTO;
import com.absjrdev.abscommerce.order.dto.OrderResponseDTO;
import com.absjrdev.abscommerce.order.dto.UpdateOrderStatusRequestDTO;
import com.absjrdev.abscommerce.order.repository.OrderRepository;
import com.absjrdev.abscommerce.orderItem.domain.OrderItem;
import com.absjrdev.abscommerce.orderItem.repository.OrderItemRepository;
import com.absjrdev.abscommerce.product.domain.Product;
import com.absjrdev.abscommerce.product.repository.ProductRepository;
import com.absjrdev.abscommerce.user.domain.User;
import com.absjrdev.abscommerce.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found. Id: " + id
                        )
                );
    }

    @Transactional
    public Order create(CreateOrderRequestDTO request) {

        User client = userRepository.findById(request.getClientId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found. Id: " + request.getClientId()));

        Order order = new Order(
                null,
                Instant.now(),
                OrderStatus.WAITING_PAYMENT,
                client
        );

        order = orderRepository.save(order);

        for (OrderItemRequestDTO itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found. Id: " + itemRequest.getProductId()
                            )
                    );

            OrderItem orderItem = new OrderItem(
                    order,
                    product,
                    itemRequest.getQuantity(),
                    product.getPrice()
            );

            order.getItems().add(orderItem);
        }

        orderItemRepository.saveAll(order.getItems());

        return order;
    }

    @Transactional
    public Order updateStatus(Long id, UpdateOrderStatusRequestDTO request) {

        Order order = findById(id);

        OrderStatus currentStaus = order.getOrderStatus();
        OrderStatus newStatus = request.status();

        validationStatusTransition(currentStaus, newStatus);

        order.setOrderStatus(newStatus);
        return orderRepository.save(order);
    }

    private void validationStatusTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        if (currentStatus == OrderStatus.WAITING_PAYMENT && newStatus == OrderStatus.PAID) {
            throw new BusinessException("Payment must be through the payment endpoint");
        }

        if (currentStatus == OrderStatus.PAID
                && newStatus == OrderStatus.SHIPPED) {
            return;
        }

        if (currentStatus == OrderStatus.SHIPPED
                && newStatus == OrderStatus.DELIVERED) {
            return;
        }

        if ((currentStatus == OrderStatus.WAITING_PAYMENT
                || currentStatus == OrderStatus.PAID)
                && newStatus == OrderStatus.CANCELLED) {
            return;
        }

        throw new BusinessException(
                "Invalid order status transition: "
                        + currentStatus
                        + " -> "
                        + newStatus
        );
    }

}