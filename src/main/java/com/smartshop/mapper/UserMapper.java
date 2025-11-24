package com.smartshop.mapper;

import com.smartshop.dto.user.CreateUserDto;
import com.smartshop.dto.user.LogInDTO;
import com.smartshop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(LogInDTO dto);

    User toEntity(CreateUserDto dto);

    LogInDTO toDto(User user);
}
