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
    public User getUser(String account, String pwd) {
        User user=userMapper.getUser(account,pwd);
        return user;
    }

    @Override
    public User addUser(String account, String pwd, String name, String sex, String age, String tel, String address) {
        User user=userMapper.addUser(account,pwd,name,sex,age,tel,address);
        return user;
    }

    @Override
    public User checkAccount(String account) {
        User user=userMapper.checkAccount(account);
        return user;
    }

    @Override
    public Integer checkSex(String name) {
        Integer num=userMapper.checkSex(name);
        return num;
    }
}
