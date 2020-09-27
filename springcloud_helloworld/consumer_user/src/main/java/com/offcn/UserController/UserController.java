package com.offcn.UserController;

import com.offcn.pojo.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //查询用户
    @GetMapping("/user")
    public User getUser(@RequestParam("id") Integer id) {

        User user = userService.getUserById(id);
        return user;
    }

    //买电影票
    @GetMapping("/buyMovie")
    public Map<String, Object> buyMovie(@RequestParam("id") Integer userId) {


        Map<String, Object> map = userService.buyMovie(userId);
        return map;
    }
}