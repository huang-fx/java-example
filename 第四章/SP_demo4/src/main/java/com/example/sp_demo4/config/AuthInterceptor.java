package com.example.sp_demo4.config;

import com.example.sp_demo4.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtils jwtUtils;
    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        String token = request.getHeader("auth");
        if (token == null || token.equals("")) {
            response.setStatus(401);
            return false;
        }
        if (jwtUtils.verifyToken(token)) {
            return true;
        } else {
            response.setStatus(401);
            return false;
        }
    }
}
