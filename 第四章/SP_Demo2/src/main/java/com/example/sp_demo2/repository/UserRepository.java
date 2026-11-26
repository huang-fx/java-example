package com.example.sp_demo2.repository;

import com.example.sp_demo2.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User searchUserById(String id) {
        User user = new User();
        user.setId(id);
        user.setUsername("admin");
        user.setPassword("12345");
        return user;
    }
}
