package com.example.admin.mapper;

import com.example.admin.entity.User.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findAll();
    List<User> findAllwaiting();
    User getNext();
    User findName(String name);
    void createUser(User user);
    void submitPhoto(String photoURL, String userid, long trigger_timestamp);
    void updateUser(User user);
    void deleteUser(int id);
}
