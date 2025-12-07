package com.smartshop.service;

import com.smartshop.config.PasswordUtil;
import com.smartshop.dto.user.CreateUserDto;
import com.smartshop.dto.user.LogInDTO;
import com.smartshop.entity.User;
import com.smartshop.exeptions.BusinessRuleViolationException;
import com.smartshop.mapper.UserMapper;
import com.smartshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordUtil passwordUtil;
    @InjectMocks
    private UserService userService;

    @Test
    void createUserSuccess(){
        CreateUserDto user =  new CreateUserDto();
        user.setUsername("user");
        user.setPassword("pass");

        User mappedUser = new User();
        mappedUser.setUserName("user");

        when(userRepository.findByUserName("user")).thenReturn(null);
        when(userMapper.toEntity(user)).thenReturn(mappedUser);
        when(passwordUtil.passwordHash(user.getPassword())).thenReturn("password");
        when(userRepository.save(any(User.class))).thenAnswer(i->i.getArguments()[0]);

        User result = userService.create(user);

        assertNotNull(result);
        assertEquals("password",result.getPassword());
        assertEquals("user",result.getUserName());
    }

    @Test
    void UserAlreadyExitException(){
        CreateUserDto user =  new CreateUserDto();
        user.setUsername("user");
        user.setPassword("pass");

        User mappedUser = new User();
        mappedUser.setUserName("user");
        mappedUser.setPassword("password");

        when(userRepository.findByUserName("user")).thenReturn(mappedUser);

        BusinessRuleViolationException exception = assertThrows(BusinessRuleViolationException.class,()->{
            userService.create(user);
        });

        assertEquals("user already exist",exception.getMessage());
        verify(userRepository,never()).save(any());
    }

    @Test
    void LogInSuccess(){
        LogInDTO user = new LogInDTO();
        user.setUsername("user");
        user.setPassword("pass");

        User mappdeUser= new User();
        mappdeUser.setUserName("user");
        mappdeUser.setPassword("password");

        when(userRepository.findByUserName("user")).thenReturn(mappdeUser);
        when(passwordUtil.checkPassword(user.getPassword(),mappdeUser.getPassword())).thenReturn(true);

        User login = userService.LogIn(user);

        assertNotNull(login);
        assertEquals("password",login.getPassword());
    }



}