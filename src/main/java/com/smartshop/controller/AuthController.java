package com.smartshop.controller;

import com.smartshop.dto.user.CreateUserDto;
import com.smartshop.dto.user.LogInDTO;
import com.smartshop.entity.User;
import com.smartshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<?> logIn(@Valid @RequestBody LogInDTO dto, HttpServletRequest request) {
        User user = userService.LogIn(dto);
        HttpSession session = request.getSession(true);

        session.setAttribute("user", user.getId());
        return ResponseEntity.ok("your log in ");
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto dto) {
        userService.create(dto);
        return ResponseEntity.ok("your account was created ");
    }
}