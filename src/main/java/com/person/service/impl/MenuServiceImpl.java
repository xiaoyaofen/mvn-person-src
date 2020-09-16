package com.person.service.impl;

import com.person.bean.Menu;
import com.person.mapper.MenuMapper;
import com.person.service.MenuService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List getMenu() {
        List<Menu> menuList= menuMapper.getMenu();
        return menuList;
    }

    @Override
    public List<Menu> getMenuListByRoleId(Integer roleid) {
        List<Menu> list =new ArrayList<Menu>();
        list=menuMapper.getMenuListByRoleId(roleid);
        return list;
    }

//    @Override
//    public Map<String, List<Menu>> Menu(String rolesid) {
//        HashMap<String, List<Menu> > menuMap=null;
//        menuMap=new HashMap();
//        List<Menu> pMenu=menuMapper.Menu(rolesid,"0");
//        for (Menu menu:pMenu) {
//            List<Menu> sMenus=menuMapper.Menu(rolesid,String.valueOf(menu.getId()));
//            menuMap.put(menu.getMenuname(),sMenus);
//        }
//        return  menuMap;
//    }



}
