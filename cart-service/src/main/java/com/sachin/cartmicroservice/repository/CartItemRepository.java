package com.sachin.cartmicroservice.repository;

import com.sachin.cartmicroservice.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItem,String> {
}
