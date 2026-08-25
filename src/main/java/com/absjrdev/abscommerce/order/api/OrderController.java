package com.absjrdev.abscommerce.order.api;

import com.absjrdev.abscommerce.order.application.OrderService;
import com.absjrdev.abscommerce.order.domain.Order;
import com.absjrdev.abscommerce.order.dto.CreateOrderRequestDTO;
import com.absjrdev.abscommerce.order.dto.OrderResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Orders", description = "Operations related to orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(
            summary = "Retrieve all orders",
            description = "Returns a list of all registered orders."
    )
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {

        List<OrderResponseDTO> list = orderService.findAll()
                .stream()
                .map(OrderResponseDTO::new)
                .toList();

        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Retrieve an order by ID",
            description = "Returns the details of an order identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {

        Order order = orderService.findById(id);

        return ResponseEntity.ok(new OrderResponseDTO(order));
    }

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order with the selected products and quantities."
    )
    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(
            @RequestBody CreateOrderRequestDTO request) {

        Order order = orderService.create(request);

        URI uri = URI.create("/orders/" + order.getId());

        return ResponseEntity
                .created(uri)
                .body(new OrderResponseDTO(order));
    }
}