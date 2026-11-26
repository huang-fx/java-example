package com.example.sp_demo4.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sp_demo4.model.dto.RegisterDTO;
import com.example.sp_demo4.model.dto.UserListDTO;
import com.example.sp_demo4.model.entity.User;
import com.example.sp_demo4.model.entity.UserPage;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
//public interface UserRepository {
//    @Select("select * from user where username=#{username}")
//    public Optional<User> searchUserByUsername(String username);
//    @Insert("insert into user(username, password) values (#{username}, #{password})")
//    public int insertUser(RegisterDTO form);
//    @Select("select * from user where id=#{id}")
//    public Optional<User> getUserById(String id);
//    @Update("update user set password=#{newPassword} where id=#{id}")
//    public int updateUserPassword(String id, String newPassword);
//    @Delete("delete from user where id=#{id}")
//    public int deleteUserById(String id);
//}
public interface UserRepository extends BaseMapper<User> {
    public List<User> getUserList(UserListDTO userList, int offset);
    public int getCountUser();
    public List<UserPage> getUserPageIdList(String id);
}
