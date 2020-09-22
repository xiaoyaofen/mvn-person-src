package com.person.mapper;

import com.person.bean.Daily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SystemLogMapper {
    public int insertSystemLog(@Param("daily") Daily daily);
}
