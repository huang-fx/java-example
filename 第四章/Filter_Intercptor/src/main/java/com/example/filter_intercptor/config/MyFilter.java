package com.example.filter_intercptor.config;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
//@Component
@WebFilter(urlPatterns = "/test", filterName = "MyFilter")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("过滤器初始化了");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse var2, FilterChain chain)
    throws IOException, ServletException {
        System.out.println("来了一个请求");
        // 先把ServletRequest转化为更好用的Spring boot提供的HttpServletResquest
        HttpServletRequest httpReq = (HttpServletRequest)req;
        String path = httpReq.getRequestURI();
        if (path.equals("/test")) {
            chain.doFilter(req, var2);
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁了");
    }
}
