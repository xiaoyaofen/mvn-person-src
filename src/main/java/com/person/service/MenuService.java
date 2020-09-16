package com.person.service;

import com.person.bean.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuService {
    public List getMenu();
//    public Map<String, List<Menu>> Menu(String rolesid);
public List<Menu> getMenuListByRoleId(Integer roleid);
}
