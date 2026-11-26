package com.example.sp_demo3.service;

import com.example.sp_demo3.model.entity.User;
import com.example.sp_demo3.model.vo.UserVo;
import com.example.sp_demo3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public UserVo getUserById(int id) {
        User user = userRepository.searchById(id);
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUsername(user.getUsername());
        return userVo;
    }
    public List<UserVo> getUserByUsername(String username) {
        List<User> userList = userRepository.searchUserByUsername(username);
        List<UserVo> userVoList = new ArrayList<UserVo>();
        userList.forEach((User user) -> {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setUsername(user.getUsername());
            userVoList.add(userVo);
        });
        return userVoList;
    }
    public List<UserVo> searchUser(int id, String username, String password) {
        List<User> userList = userRepository.searchUser(id, username, password);
        List<UserVo> userVoList2 = new ArrayList<UserVo>();
        userList.forEach((User user) -> {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setUsername(user.getUsername());
            userVoList2.add(userVo);
        });
        return userVoList2;
    }
}
