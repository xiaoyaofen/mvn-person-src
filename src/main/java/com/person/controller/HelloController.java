package com.person.controller;


import com.person.bean.User;
import com.person.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){
        User user=userService.getUser("aaa","123");
        return user;
    }

    @GetMapping(value = "/test")
    public String hello(Model model) {
        String name = "jiangbei";
        model.addAttribute("name", name);
        return "index";
    }
}
