package com.absjrdev.abscommerce.payment.dto;

import com.absjrdev.abscommerce.payment.domain.paymentMethod.PaymentMethod;

public record CreatePaymentRequestDTO(
        PaymentMethod paymentMethod
) {
}