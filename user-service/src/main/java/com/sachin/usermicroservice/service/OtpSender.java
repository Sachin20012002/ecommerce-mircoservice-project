package com.sachin.usermicroservice.service;

public interface OtpSender{
    void sendOtp(String to, String otp);
}
