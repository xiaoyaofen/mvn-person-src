package com.person.service.impl;

import com.person.bean.User;
import com.person.mapper.WxMapper;
import com.person.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxServiceImpl implements WxService {

    @Autowired
    WxMapper wxMapper;

    @Override
    public User login(String account, String pwd) {
        User user=wxMapper.login(account,pwd);
        return user;
    }
}
