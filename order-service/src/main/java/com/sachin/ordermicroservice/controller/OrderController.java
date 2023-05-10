package com.sachin.ordermicroservice.controller;

import com.sachin.ordermicroservice.entity.Order;
import com.sachin.ordermicroservice.response.GenericResponse;
import com.sachin.ordermicroservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public GenericResponse<Order> placeOrder(@RequestBody Order order) {
        return GenericResponse.<Order>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(orderService.placeOrder(order))
                .build();
    }
}
