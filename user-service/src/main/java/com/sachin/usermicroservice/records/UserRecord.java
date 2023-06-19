package com.sachin.usermicroservice.records;

import com.sachin.usermicroservice.enums.Role;
import com.sachin.usermicroservice.model.User;

import java.util.List;

public record UserRecord(String id, String name, String email, String phoneNumber, List<Role> roles) {
    public UserRecord(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber(), user.getRoles());
    }

}
