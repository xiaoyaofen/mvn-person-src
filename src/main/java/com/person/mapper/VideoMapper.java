package com.person.mapper;

import com.person.bean.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoMapper {
    public List<Menu> findVideo(@Param("id") String id);
}
