package com.person.service;

import com.person.bean.User;

public interface UserService {
    public User getUser(String account, String pwd);
    public User addUser(String account, String pwd, String name, String sex, String age, String tel, String address);
    public User checkAccount(String account);
    public Integer checkSex(String name);
}
