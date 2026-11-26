package com.example.sp_demo4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor).excludePathPatterns(
//            "/user/login",
//            "/user/register",
//            "/user/getCode",
//            "/user/list"
//            "/user/testBase64",
//            "/user/decodeBase64"
//        );
//    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 前端携带了cookie cros就一定要设置允许携带cookie
        // 而且如果cors设置了允许携带cookie allowHeader和allowOrigin就都不能为*了
        registry.addMapping("/**")
        .allowCredentials(true)
        .allowedOrigins("http://localhost:5173/")
        .allowedMethods("*");
    }
}
