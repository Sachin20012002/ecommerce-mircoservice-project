package com.sachin.usermicroservice.repository;

import com.sachin.usermicroservice.model.OtpEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<OtpEntity,String> {
     Optional<OtpEntity> findByPhoneNumber(String phoneNumber);
}
