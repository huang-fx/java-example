package com.example.filter_intercptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        System.out.println("我是test接口");
        return "ok";
    }
    @GetMapping("/test2")
    public String test2() {
        return "ok123";
    }
}
