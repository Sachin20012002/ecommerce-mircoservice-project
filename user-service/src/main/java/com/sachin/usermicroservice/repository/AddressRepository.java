package com.sachin.usermicroservice.repository;

import com.sachin.usermicroservice.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address,String> {
}
