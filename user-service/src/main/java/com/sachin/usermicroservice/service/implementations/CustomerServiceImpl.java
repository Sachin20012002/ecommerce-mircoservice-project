package com.sachin.usermicroservice.service.implementations;

import com.sachin.usermicroservice.exception.NotFoundException;
import com.sachin.usermicroservice.model.Customer;
import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.repository.CustomerRepository;
import com.sachin.usermicroservice.response.LoginResponse;
import com.sachin.usermicroservice.service.CustomerService;
import com.sachin.usermicroservice.service.MessagingService;
import com.sachin.usermicroservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    @Lazy
    private MessagingService messagingService;

    @Transactional
    @Override
    public void createCustomer(User user) {
        Customer customer = customerRepository.save(Customer.builder()
                .userDetails(user).build());
        messagingService.createCartForCustomer(customer.getId());
    }

    @Override
    public void updateCustomerCartId(String customerId, String cartId) {
        Customer customer = getById(customerId);
        customer.setCartId(cartId);
        customerRepository.save(customer);
    }


    @Override
    public Customer getCustomerByUser(User user) {
        return customerRepository.findByUserDetails(user)
                        .orElseThrow(() -> new NotFoundException("Customer not found"));
    }


    public Customer getById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer Id not found"));
    }
}
