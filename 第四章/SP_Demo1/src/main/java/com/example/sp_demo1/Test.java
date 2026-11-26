package com.example.sp_demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {
    @GetMapping("/hello")
    public String getMethod() {
        return "Hello World!";
    }
    @GetMapping("/getJson")
    public TestUser getJson() {
//        Map<String, String> mapObj = new HashMap<>();
//        mapObj.put("a", "123");
//        mapObj.put("b", "456");
        return new TestUser();
    }
}