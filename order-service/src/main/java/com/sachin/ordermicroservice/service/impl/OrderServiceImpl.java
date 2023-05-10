package com.sachin.ordermicroservice.service.impl;

import com.sachin.ordermicroservice.entity.Order;
import com.sachin.ordermicroservice.repository.OrderRepository;
import com.sachin.ordermicroservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public Order placeOrder(Order order) {
        System.out.println(order);
        return orderRepository.save(order);
    }
}
