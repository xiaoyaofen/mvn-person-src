package com.person.service;

import com.person.bean.User;

public interface WxService {
    public User login(String account,String pwd);
}
