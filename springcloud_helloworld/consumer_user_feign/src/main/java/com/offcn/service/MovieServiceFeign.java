package com.offcn.service;

import com.offcn.pojo.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用指定服务名称 “PROVIDERMOVIE” 的 @GetMapping("/movie") 映射方法
 * 这个方法声明与电影服务端Controller映射的方法声明一致即可。
 */
@Service
@FeignClient(value = "PROVIDERMOVIE",fallback = MovieFeignExceptionHandlerService.class)  //与被调用的服务名称一致
public interface MovieServiceFeign {
    @GetMapping("/movie")
    public Movie getNewMovie();
//    {
//        return movieService.getNewMovie();
//    }



}
