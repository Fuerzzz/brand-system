package com.chinahitech.shop.service;

import com.chinahitech.shop.bean.User;
import com.chinahitech.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper UserMapper;

    public void UserServiceImpl(UserMapper userMapper) {
        this.UserMapper = userMapper;
    }

    public User login(String username, String password) {
        return UserMapper.login(username, password);
    }
}
