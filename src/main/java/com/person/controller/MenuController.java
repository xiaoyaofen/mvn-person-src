package com.person.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.person.bean.Admin;
import com.person.bean.Menu;
import com.person.bean.User;
import com.person.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//个人中心的菜单
    @RequestMapping("/centerMenu")
    @ResponseBody
    public String centerMenu(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(33333);
        User user= (User) request.getSession().getAttribute("user");
            return new Gson().toJson(user);
    }

    @RequestMapping("/getMenu")
    @ResponseBody
    public String getMenu(HttpServletRequest request, HttpServletResponse response) {
        List menuList =  menuService.getMenu();
        request.setAttribute("menuList", menuList);
        return JSON.toJSONString(menuList);
    }

    @RequestMapping("/Menu")
    @ResponseBody
    public String Menu(HttpServletRequest request, Model model) {
        Admin admin= (Admin) request.getSession().getAttribute("admin");
        model.addAttribute("adminMame",admin.getName());
        Integer roleid= admin.getRoleid();
        List<Menu> menuList =  menuService.getMenuListByRoleId(roleid);
        return JSON.toJSONString(menuList);
    }
}
