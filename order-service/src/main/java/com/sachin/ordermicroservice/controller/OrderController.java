package com.sachin.ordermicroservice.controller;

import com.sachin.ordermicroservice.entity.Order;
import com.sachin.ordermicroservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).body(orderService.placeOrder(order));
    }
}
