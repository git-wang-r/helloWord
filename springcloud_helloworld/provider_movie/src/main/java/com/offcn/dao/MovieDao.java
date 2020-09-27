package com.offcn.dao;

import com.offcn.pojo.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class MovieDao {
    //获取一个电影信息的方法
    public Movie getNewMovie(){
        Movie movie = new Movie();
        movie.setId(1);
        movie.setMovieName("战狼");
        return movie;
    }
}
