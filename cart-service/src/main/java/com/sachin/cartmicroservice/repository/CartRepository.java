package com.sachin.cartmicroservice.repository;

import com.sachin.cartmicroservice.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart,String> {
    Optional<Cart> findByCustomerId(String customerId);
}
