package com.absjrdev.abscommerce.payment.application;

import com.absjrdev.abscommerce.exception.BusinessException;
import com.absjrdev.abscommerce.exception.ResourceNotFoundException;
import com.absjrdev.abscommerce.order.domain.Order;
import com.absjrdev.abscommerce.order.domain.orderStatus.OrderStatus;
import com.absjrdev.abscommerce.order.dto.UpdateOrderStatusRequestDTO;
import com.absjrdev.abscommerce.order.repository.OrderRepository;
import com.absjrdev.abscommerce.payment.domain.Payment;
import com.absjrdev.abscommerce.payment.dto.CreatePaymentRequestDTO;
import com.absjrdev.abscommerce.payment.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Payment create(Long orderId, CreatePaymentRequestDTO request) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found. Id: " + orderId
                        )
                );

        if (order.getOrderStatus() != OrderStatus.WAITING_PAYMENT) {
            throw new BusinessException(
                    "Order is not waiting for payment."
            );
        }

        Payment payment = new Payment(
                null,
                Instant.now(),
                request.paymentMethod(),
                order);

        order.setPayment(payment);
        order.setOrderStatus(OrderStatus.PAID);

        return paymentRepository.save(payment);
    }

}