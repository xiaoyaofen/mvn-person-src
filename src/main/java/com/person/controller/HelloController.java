package com.person.controller;


import com.person.bean.Admin;
import com.person.bean.Product;
import com.person.bean.User;
import com.person.service.UserService;
import com.person.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;

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
    public String firmMenu(Model model, HttpServletRequest request) {
        Admin admin= (Admin) request.getSession().getAttribute("admin");
        model.addAttribute("adminMame",admin.getName());
        return "firmMenu";
    }
//招聘首页
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

    @GetMapping(value = "/study")
    public String study(Model model, HttpServletRequest request) {
        User user= (User) request.getSession().getAttribute("user");
        List<Product> list=videoService.studyProduct(String.valueOf(user.getId()));
        model.addAttribute("list",list);
        return "Study";
    }

    @GetMapping(value = "/myjianli")
    public String myjianli() {
        return "myjianli";
    }




//人脸识别注册
    @GetMapping(value = "/face")
    public String face() {
        return "face_registrate";
    }


//人脸识别
    @GetMapping(value = "/face1")
    public String face1() {
        return "face_search";
    }


}
