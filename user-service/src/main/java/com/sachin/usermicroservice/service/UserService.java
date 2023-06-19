package com.sachin.usermicroservice.service;

import com.sachin.usermicroservice.enums.Role;
import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.response.LoginResponse;

import java.util.List;

public interface UserService {
    User save(User user);

    List<User> getAllUsers();

    User getById(String userId);

    boolean isExistsByPhoneNumber(String phoneNumber);

    User createUser(String phoneNumber, Role role);

    User getByPhoneNumber(String phoneNumber);
}
