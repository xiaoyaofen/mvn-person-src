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

    @GetMapping(value = "/userLogin")
    public String userLogin() {

        return "UserLogin";
    }


    @GetMapping(value = "/person")
    public String person() {
        return "Person";
    }


    @GetMapping(value = "/Register")
    public String Register() {

        return "Register";
    }

    @GetMapping(value = "/Job")
    public String Job() {

        return "Job";
    }

    @GetMapping(value = "/menu")
    public String menu() {
        return "PersonMenu";
    }


    @GetMapping(value = "/feedback")
    public String feedback() {
        return "Feedback";
    }


    @GetMapping(value = "/myresume")
    public String resume() {
        return "myresume";
    }



    @GetMapping(value = "/collect")
    public String Collect() {
        return "Collect";
    }

    @GetMapping(value = "/firmMenu")
    public String firmMenu() {
        return "firmMenu";
    }

    @GetMapping(value = "/first")
    public String first() {
        return "First";
    }


    @GetMapping(value = "/center")
    public String center() {
        return "personCenter";
    }


    @GetMapping(value = "/qo")
    public String qo() {
        return "test";
    }


    @GetMapping(value = "/jianli")
    public String jianli() {
        return "jianli";
    }






}
