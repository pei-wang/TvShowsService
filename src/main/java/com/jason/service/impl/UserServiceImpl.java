package com.jason.service.impl;

import com.jason.model.User;
import com.jason.repository.UserRepository;
import com.jason.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean login(String username, String password) {
        User loginUser = userRepository.findByUsernameAndPassword(username, password);
        if (null != loginUser) {
            ValueOperations<String, String> opt = redisTemplate.opsForValue();
            String uuid = UUID.randomUUID().toString();
            opt.set(uuid, loginUser.getId() + "");
        }
        return false;
    }

    @Override
    public boolean save(User user) {
        return null != userRepository.save(user);
    }
}