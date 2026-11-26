package com.example.sp_demo3.controller;

import com.example.sp_demo3.model.vo.BaseVo;
import com.example.sp_demo3.model.vo.UserVo;
import com.example.sp_demo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    // 根据id查询用户
    @GetMapping("/getUserById")
    public BaseVo<UserVo> getUserById(@RequestParam int id) {
        UserVo userVo = userService.getUserById(id);
        return BaseVo.success(userVo);
    }
    // 根据用户名模糊查询用户
    @GetMapping("/getUserByUsername")
    public BaseVo<List<UserVo>> getUserByUsername(@RequestParam String username) {
        List<UserVo> userVoList = userService.getUserByUsername(username);
        return BaseVo.success(userVoList);
    }
    // 根据用户id 用户名 密码一起查询 其中用户名和密码支持模糊查询
    @GetMapping("/searchUser")
    public BaseVo<List<UserVo>> searchUser(int id, String username, String password) {
        List<UserVo> userVoList = userService.searchUser(id, username, password);
        return BaseVo.success(userVoList);
    }
}
