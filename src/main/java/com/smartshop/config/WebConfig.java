package com.smartshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/api/auth/create",
                        "/api/client/**",
                        "/api/product/**",
                        "api/order/**")
                .excludePathPatterns("api/product/all",
                        "api/product/{id}");

        registry.addInterceptor(loginInterceptor).addPathPatterns("api/product/all",
                "api/product/{id}");
    }
}
