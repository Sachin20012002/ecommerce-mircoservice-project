package com.sachin.usermicroservice.service;

import com.sachin.usermicroservice.service.implementations.TwilioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserServiceImplTest {


    @Autowired
    private TwilioService twilioService;


    @Test
    void sendOtp() {
        twilioService.sendOtp("+919940491149","123456");
    }

    @Test
    void login() {
    }
}