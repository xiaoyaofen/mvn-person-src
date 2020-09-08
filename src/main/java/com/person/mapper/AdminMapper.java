package com.person.mapper;

import com.person.bean.Params;
import com.person.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Mapper
@Component
public interface AdminMapper {

    List<UserInfo> getUserByAdmin(@Param("condition") HashMap<String, Object> condition, @Param("limit") Integer limit, @Param("page") Integer page);

    Integer getUserByAdminOfNum(@Param("condition") HashMap<String, Object> condition);

    public List<Params> getParamList(@Param("start") Integer start,
                                     @Param("pageSize") Integer pageSize,
                                     @Param("name") String name,
                                     @Param("type") String type
    );
    public int getParamListCount(@Param("name") String name,
                                 @Param("type") String type

    );
    public List<String> paramsTypeList();
}
