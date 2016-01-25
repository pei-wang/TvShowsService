package com.jason.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/")
    public String index() {

        return "hello controller";
    }
}
