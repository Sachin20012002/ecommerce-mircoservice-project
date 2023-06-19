package com.sachin.usermicroservice.service;

import com.sachin.usermicroservice.response.LoginResponse;

public interface AuthenticateService {
    LoginResponse login(String phoneNumber, String otp);
    void signup(String phoneNumber);
}
