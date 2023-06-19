package com.sachin.usermicroservice.service.implementations;

import com.sachin.usermicroservice.enums.Role;
import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.response.LoginResponse;
import com.sachin.usermicroservice.service.AuthenticateService;
import com.sachin.usermicroservice.service.CustomerService;
import com.sachin.usermicroservice.service.OtpService;
import com.sachin.usermicroservice.service.UserService;
import com.sachin.usermicroservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final CustomerService customerService;
    private final OtpService otpService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse login(String phoneNumber, String otp) {
        otpService.verifyOtp(phoneNumber, otp);
        return getLoginResponse(phoneNumber);
    }


    @Override
    public void signup(String phoneNumber) {
        if (!userService.isExistsByPhoneNumber(phoneNumber)) {
            customerService.createCustomer(userService.createUser(phoneNumber,Role.CUSTOMER));
        }
        otpService.sendOtp(phoneNumber);
    }

    public LoginResponse getLoginResponse(String phoneNumber) {
        User user = userService.getByPhoneNumber(phoneNumber);
        Map<String,Object> claims=new HashMap<>();
        claims.put("authorities",user.getAuthorities());
        if (user.getRoles().contains(Role.CUSTOMER))
            claims.put("customerId",customerService.getCustomerByUser(user).getId());
        // TODO: add admin and supplier Id
        return LoginResponse.builder()
                .jwtToken(jwtUtil.generateToken(claims,user))
                .userName(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }





}
