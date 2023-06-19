package com.sachin.usermicroservice.controller;

import com.sachin.usermicroservice.request.LoginRequest;
import com.sachin.usermicroservice.response.GenericResponse;
import com.sachin.usermicroservice.response.LoginResponse;
import com.sachin.usermicroservice.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticateController {
    private final AuthenticateService authenticateService;

    @PostMapping("/signup")
    public GenericResponse<String> sendOtp(@RequestBody LoginRequest loginRequest) {

        authenticateService.signup(loginRequest.getPhoneNumber());
        return GenericResponse.<String>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data("OTP sent successfully")
                .build();
    }

    @PostMapping("/login")
    public GenericResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return GenericResponse.<LoginResponse>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(authenticateService.login(loginRequest.getPhoneNumber(), loginRequest.getOtp()))
                .build();
    }
}
