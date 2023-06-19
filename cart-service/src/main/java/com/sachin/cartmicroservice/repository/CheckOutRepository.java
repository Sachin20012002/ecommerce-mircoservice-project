package com.sachin.cartmicroservice.repository;

import com.sachin.cartmicroservice.model.CheckOut;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckOutRepository extends MongoRepository<CheckOut, String> {

}
