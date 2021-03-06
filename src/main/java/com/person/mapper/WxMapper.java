package com.person.mapper;

import com.person.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface WxMapper {
    public User login(@Param("account") String account, @Param("pwd") String pwd);
}
