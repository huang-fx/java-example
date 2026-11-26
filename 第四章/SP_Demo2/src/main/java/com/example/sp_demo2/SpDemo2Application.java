package com.example.sp_demo2;

import com.example.sp_demo2.config.BaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(
//        basePackages = "com.example.sp_demo2",
//        excludeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                classes = BaseConfig.class
//        )
//)
public class SpDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpDemo2Application.class, args);
    }

}
