package com.person.service;

import com.person.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public User getUser(String account, String pwd);
    public void addUser(String account,String pwd,String name,String sex,String age,String tel,String address);
    public String checkAccount(String account);
    public String checkSex(String name);
//反馈表
    public List<Feedback> getFeedback(String userid,HashMap<String, Object> condition);
    public Integer findCount(String userid,HashMap<String, Object> condition);
//用户收藏表
    public List<Collect> getCollect(String userid, HashMap<String, Object> condition);
    public Integer findCount2(String userid,HashMap<String, Object> condition);

    public List<Contacts> getContacts(String userid, HashMap<String, Object> condition);
    public Integer findCount3(String userid,HashMap<String, Object> condition);

    public void down(String img,String id);
    public User  getimg(String id);
    public void Infor(String account,String name,Integer sex,String tel,String address,String id);
    public  Integer getsex(String name);
    public User findInfor(String id);
    public mixture introduce(String name, String pname);

    public User findbyname(String account);

    public void getuserid(String username);

    public User getFace(String faceId);

    public Params getValue(String value);

    public Params Sex(String value);

    public Params getExperience(String value);
}
