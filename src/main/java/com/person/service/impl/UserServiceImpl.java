package com.person.service.impl;

import com.person.bean.User;
import com.person.mapper.UserMapper;
import com.person.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(String account,String pwd) {
        User user=userMapper.getUser(account,pwd);
        return user;
    }
}
