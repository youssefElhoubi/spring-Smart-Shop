package com.smartshop.service;

import com.smartshop.entity.User;
import com.smartshop.mapper.UserMapper;
import com.smartshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

//    public User create()
}
