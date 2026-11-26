package com.example.sp_demo2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BaseConfig {
    @Bean
    public String getText() {
        return "hello";
    }
    @Bean
    public String getText2() {
        return "hello2";
    }
    @Bean
    public Dog dog() {
        return new Dog();
    }
}
