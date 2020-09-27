package com.offcn.dao;

import com.offcn.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    //查询用户


    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUserName("张三");
        return user;
    }
}