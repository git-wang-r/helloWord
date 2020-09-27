package com.offcn.service;

import com.offcn.dao.UserDao;
import com.offcn.pojo.Movie;
import com.offcn.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private MovieServiceFeign movieServiceFeign;
   /* @Autowired
    private RestTemplate restTemplate;*/

    /**
     * 根据ID得到用户对象
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        User user = userDao.getUser(id);
        return user;
    }

    /**
     * 购买最新的电影票
     * @param id 用户ID
     * @return
     */
    public Map<String, Object> buyMovie(Integer id) {
        /*redisTemplate.opsForHash().put("1","2","3");

        System.out.println(redisTemplate.opsForHash().get("1","2"));*/
        Map<String, Object> result = new HashMap();
        //1.查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);
        //2.查到最新电影票
        //参数一个是在    eureka的地址  一个是 类型
       /* Movie movie = restTemplate.getForObject("http://PROVIDERMOVIE/movie", Movie.class);*/

        Movie movie = movieServiceFeign.getNewMovie();
        //TODO 暂时为null
        result.put("movie", movie);
        return result;
    }
}