package com.smartshop.config;

import com.smartshop.entity.User;
import com.smartshop.enums.Role;
import com.smartshop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendError(401, "Unauthorized: Please login first");
        }
        assert session != null;
        Long id = (Long) session.getAttribute("user");
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("something is wrong ");
        });
        return true;
    }
}