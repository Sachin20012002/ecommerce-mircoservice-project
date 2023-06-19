package com.sachin.usermicroservice.service;


import com.sachin.usermicroservice.model.Customer;
import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.response.LoginResponse;

public interface CustomerService {
    void createCustomer(User user);

    void updateCustomerCartId(String customerId, String cartId);
    Customer getCustomerByUser(User user);
}
