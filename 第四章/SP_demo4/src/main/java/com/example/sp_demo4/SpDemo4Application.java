package com.example.sp_demo4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.sp_demo4.repository")
@SpringBootApplication
@EnableScheduling
public class SpDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(SpDemo4Application.class, args);
    }

}
