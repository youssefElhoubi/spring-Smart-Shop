package com.smartshop.mapper;

import com.smartshop.dto.user.CreateUserDto;
import com.smartshop.dto.user.LogInDTO;
import com.smartshop.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(LogInDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setUserName(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

    public User toEntity(CreateUserDto dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setUserName(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }

    public LogInDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        LogInDTO dto = new LogInDTO();
        dto.setUsername(user.getUserName());
        dto.setPassword(user.getPassword());
        return dto;
    }
}