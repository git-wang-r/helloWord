package com.offcn.service;

import com.offcn.dao.MovieDao;
import com.offcn.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Value("${server.port}")
    private String port;
    @Autowired
    MovieDao movieDao;

    public Movie getNewMovie() {
        System.out.println("端口号"+port);
        return movieDao.getNewMovie();
    }
}
