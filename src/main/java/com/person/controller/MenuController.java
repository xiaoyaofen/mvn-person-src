package com.person.controller;

import com.alibaba.fastjson.JSON;
import com.person.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;


    @RequestMapping("/getMenu")
    @ResponseBody
    public String getMenu(HttpServletRequest request, HttpServletResponse response) {
        List menuList =  menuService.getMenu();
        request.setAttribute("menuList", menuList);
        return JSON.toJSONString(menuList);
    }
}
