package com.person.mapper;

import com.person.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface MenuMapper {
    public List getMenu();
//    public List<Menu> Menu(@Param("roleid")String roleid, @Param("pid")String pid);
    public List<Menu> getMenuListByRoleId(@Param("roleid")Integer roleid);
}


