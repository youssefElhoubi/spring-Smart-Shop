package com.smartshop.config;

import com.smartshop.entity.User;
import com.smartshop.enums.Role;
import com.smartshop.repository.UserRepository;
import com.smartshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null|| session.getAttribute("user")== null){
            response.sendError(401, "Unauthorized: Please login first");
        }
        assert session != null;
        Long id = (Long) session.getAttribute("user");
        User user = userRepository.findById(id).orElseThrow(()->{new RuntimeException("something is wrong ");
            return null;
        });
        if(user.getRole()!= Role.ADMIN){
            response.sendError(403 , "you are not authorized to access this rout ");
            return false;
        }

        return true;
    }
}
