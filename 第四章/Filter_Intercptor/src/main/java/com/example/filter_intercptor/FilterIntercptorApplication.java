package com.example.filter_intercptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class FilterIntercptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterIntercptorApplication.class, args);
    }

}
