package com.sachin.usermicroservice.repository;

import com.sachin.usermicroservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    boolean existsByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);
}
