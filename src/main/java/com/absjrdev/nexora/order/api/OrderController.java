package com.absjrdev.nexora.order.api;

import com.absjrdev.nexora.order.domain.Order;
import com.absjrdev.nexora.order.application.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Orders", description = "Operations related to orders")
public
class OrderController {

    @Autowired
    private
    OrderService orderService;

    @Operation(
            summary = "Retrieve all orders",
            description = "Retrieve a list of all registered orders"
    )
    @GetMapping
    public
    ResponseEntity<List<Order>> findAll() {
        List<Order> list = orderService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(
            summary = "Retrieve a order by ID",
            description = "Returns the details of a order identified by the provided ID."
    )
    @GetMapping(value = "/{id}")
    public
    ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }
}
