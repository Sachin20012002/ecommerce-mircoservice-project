package com.sachin.ordermicroservice.repository;

import com.sachin.ordermicroservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
