package com.example.sp_demo1;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestParams {
    // 携带的参数分别获取
    @GetMapping("/api1")
    public String getApi1(
            @RequestParam (name = "id", required = false, defaultValue = "234") String mid,
            @RequestParam String name
    ){
        System.out.println(mid + ":" + name);
        return "api1 is success!";
    }
    // 携带的参数作为一个对象获取
    @GetMapping("/api2")
    public String getApi2(@ModelAttribute Api2Params params) {
        System.out.println(params.getId() + ":" + params.getName());
        return "api2 is success!";
    }
    // 携带参数的post请求+参数附带在请求头上
    @PostMapping("/api3")
    public String postApi3(@RequestBody PostBody body, @RequestHeader("token") String token) {
        System.out.println(body.getPassword() + ":" + body.getUsername() + ":" + token);
        return "api3 is success!";
    }
    // 请求路径上直接携带参数
    @GetMapping("/api4/{id}")
    public String getApi4(@PathVariable String id) {
        System.out.println(id);
        return "api4 is success!";
    }
    // get请求的简洁写法
    @GetMapping("/api5")
    public String getApi5(String id, String name) {
        System.out.println(id + ":" + name);
        return "api5 is success!";
    }
    // 对于post请求 如果以json形式前后端通讯 则不能简写 只有以form表单形式进行前后端通讯 才可以用简洁写法
    @PostMapping("/api6")
    public String getApi6(PostBody body) {
        System.out.println(body.getUsername() + ":" + body.getPassword());
        return "api6 is success!";
    }
}