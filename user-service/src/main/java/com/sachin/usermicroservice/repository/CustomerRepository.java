package com.sachin.usermicroservice.repository;

import com.sachin.usermicroservice.model.Customer;
import com.sachin.usermicroservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    Optional<Customer> findByUserDetails(User user);
}
