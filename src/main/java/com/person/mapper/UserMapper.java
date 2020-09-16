package com.person.mapper;

import com.person.bean.Collect;
import com.person.bean.Feedback;
import com.person.bean.User;
import com.person.bean.mixture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Mixer;
import java.util.HashMap;
import java.util.List;

@Mapper
@Component
public interface UserMapper {
    public User getUser(@Param("account") String account, @Param("pwd") String pwd);
    public void addUser(@Param("account") String account, @Param("pwd") String pwd, @Param("name") String name, @Param("sex") String sex,@Param("age") String age, @Param("tel") String tel, @Param("address") String address);
    public String checkAccount(@Param("account")String account);
    public  String checkSex(@Param("name")String name);

    public List<Feedback> getFeedback(@Param("userid")String userid,@Param("map")HashMap<String, Object> condition);
    public Integer findCount(@Param("userid")String userid,@Param("map")HashMap<String, Object> condition);


    public List<Collect> getCollect(@Param("userid")String userid, @Param("map")HashMap<String, Object> condition);
    public Integer findCount2(@Param("userid")String userid,@Param("map")HashMap<String, Object> condition);
    public void down(@Param("img")String img,@Param("id")String id);
    public User getimg(@Param("id")String id);
    public void Infor(@Param("account")String account,@Param("name")String name,@Param("sex")Integer sex,@Param("tel")String tel,@Param("address")String address,@Param("id")String id);
    public  Integer getsex(@Param("name")String name);
    public User findInfor(@Param("id")String id);

    public mixture introduce(@Param("name")String name,@Param("pname")String pname);
}
