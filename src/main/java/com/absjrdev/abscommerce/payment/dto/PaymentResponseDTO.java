package com.absjrdev.abscommerce.payment.dto;

import com.absjrdev.abscommerce.payment.domain.Payment;
import com.absjrdev.abscommerce.payment.domain.paymentMethod.PaymentMethod;

import java.time.Instant;

public record PaymentResponseDTO(
        Long id,
        Instant moment,
        Long orderId,
        PaymentMethod paymentMethod
) {

    public PaymentResponseDTO(Payment payment) {
        this(
                payment.getId(),
                payment.getMoment(),
                payment.getOrder().getId(),
                payment.getPaymentMethod()
        );
    }
}