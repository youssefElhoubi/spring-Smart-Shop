package com.smartshop.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
    public String passwordHash(String plainText){
        return BCrypt.hashpw(plainText,BCrypt.gensalt());
    }
    public boolean checkPassword(String plainText,String hashedPassword){
        return BCrypt.checkpw(plainText,hashedPassword);
    }
}
