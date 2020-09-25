package com.person.mapper;

import com.person.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {
    public User getUser(@Param("account") String account, @Param("pwd") String pwd);
    public void addUser(@Param("account") String account, @Param("pwd") String pwd, @Param("name") String name, @Param("sex") String sex,@Param("age") String age, @Param("tel") String tel, @Param("address") String address);
    public String checkAccount(@Param("account")String account);
    public  String checkSex(@Param("name")String name);

    public List<Feedback> getFeedback(@Param("userid")String userid,@Param("map")HashMap<String, Object> condition);
    public Integer findCount(@Param("userid")String userid,@Param("map")HashMap<String, Object> condition);


    public List<Collect> getCollect(@Param("userid")String userid, @Param("map")HashMap<String, Object> condition);
    public Integer findCount2(@Param("userid")String userid,@Param("map")HashMap<String, Object> condition);

//我的人脉
    public List<Contacts> getContacts(@Param("userid")String userid, @Param("map")HashMap<String, Object> condition);
    public Integer findCount3(@Param("userid")String userid,@Param("map")HashMap<String, Object> condition);

    public void down(@Param("img")String img,@Param("id")String id);
    public User getimg(@Param("id")String id);
    public void Infor(@Param("account")String account,@Param("name")String name,@Param("sex")Integer sex,@Param("tel")String tel,@Param("address")String address,@Param("id")String id);
    public  Integer getsex(@Param("name")String name);
    public User findInfor(@Param("id")String id);

    public mixture introduce(@Param("name")String name,@Param("pname")String pname);


    public User findbyname(@Param("account")String account);
     public void getuserid(@Param("username")String username);


     public User getFace(@Param("faceId")String faceId);

    public Params getValue(@Param("value")String value);

    public Params Sex(@Param("value")String value);

    public Params getExperience(@Param("value")String value);


    public String updatesex(@Param("name")String name);

    public String updatexperience(@Param("name")String name);

    public void updateinfor(@Param("name")String name,@Param("sex")String sex,@Param("education")String education,@Param("tel")String tel,@Param("address")String address,@Param("id")Integer id);

    public void jobexperience(@Param("jobexperience")String jobexperience,@Param("id")String id);
    public void project(@Param("project")String project,@Param("id")String id);
    public void education(@Param("education")String education,@Param("id")String id);
    public void self(@Param("self")String self,@Param("id")String id);
}
