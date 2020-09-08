package com.person.mapper;

import com.person.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    public User getUser(@Param("account") String account, @Param("pwd") String pwd);
    public User addUser(@Param("account") String account, @Param("pwd") String pwd, @Param("name") String name, @Param("sex") String sex, @Param("age") String age, @Param("tel") String tel, @Param("address") String address);
    public User checkAccount(@Param("account") String account);
    public  Integer checkSex(@Param("name") String name);
}
