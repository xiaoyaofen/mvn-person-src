package com.person.service.impl;

import com.person.aoplog.Log;
import com.person.bean.*;
import com.person.mapper.UserMapper;
import com.person.service.UserService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Log(operationType = "用户登录", operationName = "user")
    public User getUser(String account, String pwd) {
        User user=userMapper.getUser(account,pwd);
        return user;
    }

    @Override
    @Log(operationType = "用户注册", operationName = "user")
    public void addUser(String account, String pwd, String name, String sex, String age, String tel, String address) {
        userMapper.addUser(account,pwd,name,sex,age,tel,address);

    }

    @Override
    @Log(operationType = "注册检测账号", operationName = "user")
    public String checkAccount(String account) {
        String user=userMapper.checkAccount(account);
        return user;
    }

    @Override
    @Log(operationType = "获得用户性别id", operationName = "user")
    public String checkSex(String name) {
        String num=userMapper.checkSex(name);
        return num;
    }

    @Override
    @Log(operationType = "用户求职反馈表", operationName = "user")
    public List<Feedback> getFeedback(String userid,HashMap<String, Object> condition) {
        HashMap hashMap=new HashMap();
        return userMapper.getFeedback(userid,condition);
    }

    @Override
    @Log(operationType = "用户求职反馈表记录数", operationName = "user")
    public Integer findCount(String userid,HashMap<String, Object> condition) {
        return userMapper.findCount(userid,condition);
    }

    @Override
    @Log(operationType = "用户收藏表", operationName = "user")
    public List<Collect> getCollect(String userid, HashMap<String, Object> condition) {
        return userMapper.getCollect(userid,condition);
    }

    @Override
    @Log(operationType = "用户收藏表记录数", operationName = "user")
    public Integer findCount2(String userid, HashMap<String, Object> condition) {
        return userMapper.findCount2(userid,condition);
    }

    @Override
    @Log(operationType = "用户人脉查询", operationName = "user")
    public List<Contacts> getContacts(String userid, HashMap<String, Object> condition) {
        return userMapper.getContacts(userid,condition);
    }

    @Override
    @Log(operationType = "用户人脉记录数", operationName = "user")
    public Integer findCount3(String userid, HashMap<String, Object> condition) {
        return userMapper.findCount3(userid,condition);
    }

    @Override
    @Log(operationType = "上传图片路径", operationName = "user")
    public void down(String img,String id) {
        userMapper.down(img,id);
    }

    @Override
    @Log(operationType = "获得用户头像", operationName = "user")
    public User getimg(String id) {
       return userMapper.getimg(id);
    }

    @Override
    @Log(operationType = "修改用户信息", operationName = "user")
    public void Infor( String account, String name, Integer sex, String tel, String address,String id) {
        userMapper.Infor(account,name,sex,tel,address,id);
    }

    @Override
    @Log(operationType = "获得性别id", operationName = "user")
    public Integer getsex(String name) {
        return userMapper.getsex(name);
    }

    @Override
    @Log(operationType = "获得用户信息", operationName = "user")
    public User findInfor(String id) {
        return userMapper.findInfor(id);
    }

    @Override
    @Log(operationType = "查看公司介绍和岗位介绍", operationName = "user")
    public mixture introduce(String name, String pname) {
        return userMapper.introduce(name,pname);
    }

    @Override
    @Log(operationType = "获得用户信息", operationName = "user")
    public User findbyname(String account) {
        System.out.println(account+"===========");
        return userMapper.findbyname(account);
    }

    @Override
    @Log(operationType = "获得用户id", operationName = "user")
    public void getuserid(String username) {

        userMapper.getuserid(username);
    }

    @Override
    @Log(operationType = "获得用户人脸id", operationName = "user")
    public User getFace(String faceId) {

        return userMapper.getFace(faceId);
    }

    @Override
    @Log(operationType = "获得学历id", operationName = "user")
    public Params getValue(String value) {
        return userMapper.getValue(value);
    }

    @Override
    @Log(operationType = "查找性别", operationName = "user")
    public Params Sex(String value) {
        return userMapper.Sex(value);
    }

    @Override
    @Log(operationType = "查找工作经验", operationName = "user")
    public Params getExperience(String value) {
        return userMapper.getExperience(value);
    }

    @Override
    @Log(operationType = "查找性别参数", operationName = "user")
    public String updatesex(String name) {

      String A=  userMapper.updatesex(name);
        System.out.println("1234"+A);

        return A;
    }

    @Override
    @Log(operationType = "查找学历参数", operationName = "user")
    public String updatexperience(String name) {
        return userMapper.updatexperience(name);
    }

    @Override
    @Log(operationType = "修改信息", operationName = "user")
    public void updateinfor(String name, String sex, String education, String tel, String address,Integer id) {
        userMapper.updateinfor(name,sex,education,tel,address,id);
    }

    @Override
    @Log(operationType = "修改经历", operationName = "user")
    public void jobexperience(String jobexperience,String id) {
        userMapper.jobexperience(jobexperience,id);
    }

    @Override
    @Log(operationType = "修改项目经验", operationName = "user")
    public void project(String project,String id) {
        userMapper.project(project,id);

    }

    @Override
    @Log(operationType = "修改教育背景", operationName = "user")
    public void education(String education,String id) {
        userMapper.education(education,id);

    }

    @Override
    @Log(operationType = "修改自我评价", operationName = "user")
    public void self(String self,String id) {
        userMapper.self(self,id);

    }


}
