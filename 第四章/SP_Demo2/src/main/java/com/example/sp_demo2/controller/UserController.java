package com.example.sp_demo2.controller;

import com.example.sp_demo2.model.vo.BaseVo;
import com.example.sp_demo2.model.vo.UserVo;
import com.example.sp_demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 所有的接口前面都有一个user前缀
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/searchUser")
    public BaseVo<UserVo> searchUser(@RequestParam String id) {
        UserVo userVo = this.userService.searchUser(id);
        return userVo != null ? BaseVo.success(userVo) : BaseVo.fail("失败", null);
    }
}
