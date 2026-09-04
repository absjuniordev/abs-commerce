package com.absjrdev.abscommerce.payment.api;

import com.absjrdev.abscommerce.order.dto.UpdateOrderStatusRequestDTO;
import com.absjrdev.abscommerce.payment.application.PaymentService;
import com.absjrdev.abscommerce.payment.domain.Payment;
import com.absjrdev.abscommerce.payment.dto.CreatePaymentRequestDTO;
import com.absjrdev.abscommerce.payment.dto.PaymentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Operations related to payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Operation(
            summary = "Create a new payment",
            description = "Creates a new payment by order."
    )
    @PostMapping("/{orderId}/payment")
    public ResponseEntity<PaymentResponseDTO> create(
            @PathVariable Long orderId,
            @RequestBody CreatePaymentRequestDTO request) {

        Payment payment = paymentService.create(orderId, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new PaymentResponseDTO(payment));
    }


}
