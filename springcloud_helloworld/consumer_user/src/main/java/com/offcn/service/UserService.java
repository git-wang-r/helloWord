package com.offcn.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.dao.UserDao;
import com.offcn.pojo.Movie;
import com.offcn.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RestTemplate restTemplate;

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
    @HystrixCommand(fallbackMethod = "buyMovieFallbackMethod")
    public Map<String, Object> buyMovie(Integer id) {
        /*redisTemplate.opsForHash().put("1","2","3");

        System.out.println(redisTemplate.opsForHash().get("1","2"));*/
        Map<String, Object> result = new HashMap();
        //1.查询用户信息
        User user = this.getUserById(id);
        result.put("user", user);
        //2.查到最新电影票
        //参数一个是在    eureka服务的地址  一个是 类型
        Movie movie = restTemplate.getForObject("http://PROVIDERMOVIE/movie", Movie.class);
        //TODO 暂时为null
        result.put("movie", movie);
        return result;
    }
    public Map<String,Object>  buyMovieFallbackMethod(Integer id){

        User user = new User();
        user.setId(-1);
        user.setUserName("未知用户");
        Movie movie = new Movie();
        movie.setId(-100);
        movie.setMovieName("无此电影");
        Map<String,Object> result = new HashMap<>();
        result.put("user",user);
        result.put("movice",movie);
        System.out.println(id);

        return result;

    }
}