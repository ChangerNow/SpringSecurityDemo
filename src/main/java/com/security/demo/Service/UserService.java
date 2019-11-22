package com.security.demo.Service;

import com.security.demo.Entity.User;
import com.security.demo.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByName(String username){
        return userMapper.findByName(username);
    }
}
