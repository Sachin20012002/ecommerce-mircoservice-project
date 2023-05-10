package com.sachin.usermicroservice.service;


import com.sachin.usermicroservice.model.OtpEntity;
import com.sachin.usermicroservice.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final TwilioService twilioService;


    public void sendOtp(String phoneNumber) {
        // Generate a 6-digit random OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        // Store the OTP in the database with an expiration time of 5 minutes
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);
        otpRepository.save(OtpEntity.builder()
                        .phoneNumber(phoneNumber)
                        .Otp(otp)
                        .expirationTime(expirationTime)
                        .build());


        twilioService.sendOtp(phoneNumber, otp);
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        // Check if the OTP exists in the database and is not expired
        Optional<OtpEntity> otpEntityOptional = otpRepository.findByPhoneNumber(phoneNumber);
        if (otpEntityOptional.isEmpty() || otpEntityOptional.get().getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new BadCredentialsException("OTP expired");
        }

        // Verify if the provided OTP is correct
        OtpEntity otpEntity = otpEntityOptional.get();
        if (!otpEntity.getOtp().equals(otp)) {
            throw new BadCredentialsException("Invalid OTP");
        }

        // OTP is valid, delete it from the database
        otpRepository.delete(otpEntity);
        return true;
    }
}
