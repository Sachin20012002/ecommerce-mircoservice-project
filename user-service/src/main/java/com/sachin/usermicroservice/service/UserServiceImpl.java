package com.sachin.usermicroservice.service;

import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.repository.UserRepository;
import com.sachin.usermicroservice.response.LoginResponse;
import com.sachin.usermicroservice.service.UserService;
import com.twilio.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtService jwtService;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public LoginResponse getLoginResponse(String phoneNumber) {
        User user=userRepository.findByPhoneNumber(phoneNumber);
        return LoginResponse.builder()
                .jwtToken(jwtService.generateToken(user))
                .userName(user.getName())
                .phoneNumber(phoneNumber)
                .build();
    }

    @Override
    public void sendOtp(String phoneNumber) {
       if(!userRepository.existsByPhoneNumber(phoneNumber)){
           save(User.builder().phoneNumber(phoneNumber).build());
       }
       otpService.sendOtp(phoneNumber);
    }

    @Override
    public LoginResponse login(String phoneNumber, String otp) {
        otpService.verifyOtp(phoneNumber,otp);
        return getLoginResponse(phoneNumber);
    }

}

