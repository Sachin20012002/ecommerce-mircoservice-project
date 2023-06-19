package com.sachin.usermicroservice.service;

public interface MessagingService {
    void createCartForCustomer(String customerId);
    void consumeCreateCartResponse(String createCartResponse);
    
}
