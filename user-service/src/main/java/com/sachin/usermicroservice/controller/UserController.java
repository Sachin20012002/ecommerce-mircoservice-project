package com.sachin.usermicroservice.controller;


import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.records.UserRecord;
import com.sachin.usermicroservice.response.GenericResponse;
import com.sachin.usermicroservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public GenericResponse<List<UserRecord>> getAllUsers() {
        return GenericResponse.<List<UserRecord>>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(userService.getAllUsers().stream()
                        .map(UserRecord::new)
                        .collect(Collectors.toList())).build();
    }

}
