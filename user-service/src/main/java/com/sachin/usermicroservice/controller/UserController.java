package com.sachin.usermicroservice.controller;


import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.request.LoginRequest;
import com.sachin.usermicroservice.response.GenericResponse;
import com.sachin.usermicroservice.response.LoginResponse;
import com.sachin.usermicroservice.service.OtpService;
import com.sachin.usermicroservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final OtpService otpService;

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }



    @PostMapping("/login/sendOtp")
    public GenericResponse<String> sendOtp(@RequestBody LoginRequest loginRequest) {
        userService.sendOtp(loginRequest.getPhoneNumber());
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
                    .data(userService.login(loginRequest.getPhoneNumber(),loginRequest.getOtp()))
                    .build();
    }

}
