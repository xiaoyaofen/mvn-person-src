package com.person.mapper;

import com.person.bean.Collect;
import com.person.bean.Feedback;
import com.person.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
}
