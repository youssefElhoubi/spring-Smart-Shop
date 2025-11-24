package com.smartshop.service;

import com.smartshop.config.PasswordUtil;
import com.smartshop.dto.user.CreateUserDto;
import com.smartshop.entity.User;
import com.smartshop.mapper.UserMapper;
import com.smartshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordUtil passwordUtil;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordUtil passwordUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordUtil = passwordUtil;
    }

    public User create(CreateUserDto dto) {
        User user = userRepository.findByUserName(dto.getUsername());
        if (user != null) {
            throw new RuntimeException("user already exist");
        }
        user = userMapper.toEntity(dto);
        user.setPassword(passwordUtil.passwordHash(dto.getPassword()));
        userRepository.save(user);
        return user;
    }
}
