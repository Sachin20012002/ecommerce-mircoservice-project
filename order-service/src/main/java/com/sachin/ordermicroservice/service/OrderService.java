package com.sachin.ordermicroservice.service;

import com.sachin.ordermicroservice.entity.Order;

public interface OrderService {
    Order placeOrder(Order order);
}
