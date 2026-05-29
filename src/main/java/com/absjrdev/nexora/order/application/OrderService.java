package com.absjrdev.nexora.order.application;

import com.absjrdev.nexora.order.domain.Order;
import com.absjrdev.nexora.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class OrderService {

    @Autowired
    private OrderRepository orderRepository ;

    public
    List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }


}
