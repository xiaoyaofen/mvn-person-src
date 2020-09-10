package com.person.service.impl;

import com.person.bean.Menu;
import com.person.mapper.MenuMapper;
import com.person.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List getMenu() {
        List<Menu> menuList= menuMapper.getMenu();
        return menuList;
    }


//    @Override
//    public Map<String, List<Menu>> getMenu() {
//        HashMap<String, List<Menu> > menuMap=null;
//        menuMap=new HashMap();
//        Menu menu= new Menu();
//        List<Menu> pMenu= (List<Menu>) menuMapper.getMenu();
//        menuMap.put(menu.getMenuname(),pMenu);
//        return menuMap;
//    }
}
