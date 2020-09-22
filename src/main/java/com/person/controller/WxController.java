package com.person.controller;

import com.person.bean.User;
import com.person.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/wx")
public class WxController {

    @Autowired
    WxService wxService;

    @GetMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String account=request.getParameter("account");
        String pwd=request.getParameter("pwd");
        User user=wxService.login(account,pwd);
        if (user==null){
            return null;
        }else {
            return "登录成功";
        }

    }
}
