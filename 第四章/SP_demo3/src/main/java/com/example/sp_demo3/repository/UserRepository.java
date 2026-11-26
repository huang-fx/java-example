package com.example.sp_demo3.repository;

import com.example.sp_demo3.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Repository
//public interface UserRepository extends JpaRepository <User, Integer> {
//    public User findById(int id);
//    public List<User> findByUsernameContaining(String username);
////    public List<User> findByIdAndUsernameContainingAndPasswordContaining(int id, String username, String password);
//    @Query(
//            "select u from User u where "
//            + "u.id=:id AND "
//            + "u.username LIKE %:username% AND "
//            + "u.password LIKE %:password%"
//    )
//    public List<User> searchUser(int id, String username, String password);
//}

@Mapper
public interface UserRepository{
    @Select("select * from user where id=#{id}")
    public User searchById(int id);
    @Select("select * from user where username like concat('%', #{username}, '%')")
    public List<User> searchUserByUsername(String username);
    @Select("select * from user where id=#{id} and username like concat('%', #{username}, '%') and password like concat('%', #{password}, '%')")
    public List<User> searchUser(int id, String username, String password);
}
