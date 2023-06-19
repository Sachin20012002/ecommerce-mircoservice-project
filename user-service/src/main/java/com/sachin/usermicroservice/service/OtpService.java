package com.sachin.usermicroservice.service;

public interface OtpService {
    void sendOtp(String phoneNumber);
    void verifyOtp(String phoneNumber, String otp);
}
