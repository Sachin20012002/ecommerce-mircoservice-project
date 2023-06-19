package com.sachin.usermicroservice.service.implementations;

import com.sachin.usermicroservice.enums.Role;
import com.sachin.usermicroservice.exception.BadRequestException;
import com.sachin.usermicroservice.exception.NotFoundException;
import com.sachin.usermicroservice.model.User;
import com.sachin.usermicroservice.repository.UserRepository;
import com.sachin.usermicroservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User save(User user) {
        if(isExistsByPhoneNumber(user.getPhoneNumber())) {
            throw new BadRequestException("User with phoneNumber "+user.getPhoneNumber()+" already exists");
        }
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getById(String userId){
        return userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User Not found with Id "+userId));
    }

    @Override
    public boolean isExistsByPhoneNumber(String phoneNumber){
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public User createUser(String phoneNumber, Role role) {
        return save(User.builder().phoneNumber(phoneNumber)
                .roles(List.of(role)).build());
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(()-> new NotFoundException("User does not exist which phoneNumber"+phoneNumber));
    }


}

