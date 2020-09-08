package com.person.controller;

import com.person.bean.User;
import com.person.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request, HttpServletResponse response) {
        String num = request.getParameter("num");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        String code = request.getParameter("code");
        System.out.println(account);
        if (num.toUpperCase().equals(code.toUpperCase())) {
            User user = userService.getUser(account, pwd);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                return "登录成功";
            } else {
                return "账号或密码错误";
            }
        } else {
            return "验证码错误";
        }
    }


    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        System.out.println(account);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(address);
        int num=userService.checkSex(sex);
        User user = userService.checkAccount(account);
       if (user!= null) {
            return "该账号已经存在！";
        } else {
//            User user1 = new User();
//            user1.setAccount(account);
//            user1.setPwd(password);
//            user1.setSex("num);
//            user1.setName(name);
//            user1.setAge(age);
//            user1.setTel(phone);
//            user1.setAddress(address);
           User user2= userService.addUser(account,password,String.valueOf(num),sex,age,phone,address);
                return "注册成功";

        }
    }


}
