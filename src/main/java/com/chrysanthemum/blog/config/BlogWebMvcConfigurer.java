package com.chrysanthemum.blog.config;

import com.chrysanthemum.blog.interceptor.AdminLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BlogWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/admin/login");
        pathPatterns.add("/admin/dist/**");
        pathPatterns.add("/admin/plugins/**");
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").
                excludePathPatterns(pathPatterns);
    }
}
