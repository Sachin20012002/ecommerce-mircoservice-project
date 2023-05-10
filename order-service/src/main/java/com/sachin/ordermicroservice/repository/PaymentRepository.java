package com.sachin.ordermicroservice.repository;

import com.sachin.ordermicroservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
