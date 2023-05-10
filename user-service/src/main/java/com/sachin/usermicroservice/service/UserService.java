package com.sachin.usermicroservice.service;

import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.response.LoginResponse;

public interface UserService {
    User save(User user);

    LoginResponse getLoginResponse(String phoneNumber);

    void sendOtp(String phoneNumber);

    LoginResponse login(String phoneNumber, String otp);
}
