package com.person.service.impl;

import com.person.bean.*;
import com.person.mapper.UserMapper;
import com.person.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
    public void addUser(String account, String pwd, String name, String sex, String age, String tel, String address) {
        userMapper.addUser(account,pwd,name,sex,age,tel,address);

    }

    @Override
    public String checkAccount(String account) {
        String user=userMapper.checkAccount(account);
        return user;
    }

    @Override
    public String checkSex(String name) {
        String num=userMapper.checkSex(name);
        return num;
    }

    @Override
    public List<Feedback> getFeedback(String userid,HashMap<String, Object> condition) {
        HashMap hashMap=new HashMap();
        return userMapper.getFeedback(userid,condition);
    }

    @Override
    public Integer findCount(String userid,HashMap<String, Object> condition) {
        return userMapper.findCount(userid,condition);
    }

    @Override
    public List<Collect> getCollect(String userid, HashMap<String, Object> condition) {
        return userMapper.getCollect(userid,condition);
    }

    @Override
    public Integer findCount2(String userid, HashMap<String, Object> condition) {
        return userMapper.findCount2(userid,condition);
    }

    @Override
    public void down(String img,String id) {
        userMapper.down(img,id);
    }

    @Override
    public User getimg(String id) {
       return userMapper.getimg(id);
    }

    @Override
    public void Infor( String account, String name, Integer sex, String tel, String address,String id) {
        userMapper.Infor(account,name,sex,tel,address,id);
    }

    @Override
    public Integer getsex(String name) {
        return userMapper.getsex(name);
    }

    @Override
    public User findInfor(String id) {
        return userMapper.findInfor(id);
    }

    @Override
    public mixture introduce(String name, String pname) {
        return userMapper.introduce(name,pname);
    }

    @Override
    public User findbyname(String account) {
        System.out.println(account+"===========");
        return userMapper.findbyname(account);
    }

    @Override
    public void getuserid(String username) {
         userMapper.getuserid(username);
    }

    @Override
    public User getFace(String faceId) {
        return userMapper.getFace(faceId);
    }


}
