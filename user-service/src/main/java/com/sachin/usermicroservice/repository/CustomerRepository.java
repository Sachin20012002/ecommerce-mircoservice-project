package com.sachin.usermicroservice.repository;

import com.sachin.usermicroservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
}
