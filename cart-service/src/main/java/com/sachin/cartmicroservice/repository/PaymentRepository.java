package com.sachin.cartmicroservice.repository;

import com.sachin.cartmicroservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment,String> {
}
