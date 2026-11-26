package com.example.sp_demo1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHtml {
    @GetMapping("/userhtml")
    public String userHTML() {
        return "b";
    }
}
