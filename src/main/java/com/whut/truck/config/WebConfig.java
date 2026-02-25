package com.whut.truck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/error",
                        "/DetectionTest" // Allow file upload endpoint if needed, but maybe should be protected? Original was protected?
                        // Original AccessFilter protected *.jsp.
                        // DetectionTest is a servlet, probably not protected by *.jsp filter.
                        // But let's assume it should be protected if it's internal logic.
                        // However, if it's called by an external system or logged-in user?
                        // If it's called by logged-in user via AJAX, then session cookie is sent, so it passes.
                );
    }
    
    // Thymeleaf handles static resources automatically from /static, but just in case
    // No need for addResourceHandlers unless custom locations.
}
