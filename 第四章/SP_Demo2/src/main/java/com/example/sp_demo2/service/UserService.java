package com.example.sp_demo2.service;

import com.example.sp_demo2.config.Dog;
import com.example.sp_demo2.model.entity.User;
import com.example.sp_demo2.model.vo.UserVo;
import com.example.sp_demo2.repository.UserRepository;
import com.example.sp_demo2.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TimeUtil timeUtil;
    @Autowired
    String getText;
//    @Autowired
//    Dog dog;
    Dog dog;
    UserService(Dog dog) {
        this.dog = dog;
    }
    @Autowired
    @Qualifier("getText2")
    String abc;
    public UserVo searchUser(String id) {
        System.out.println(timeUtil.getNowTime());
        System.out.println(getText);
        dog.sayHi();
        System.out.println(abc);
        User user = this.userRepository.searchUserById(id);
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        return userVo;
    }
}
