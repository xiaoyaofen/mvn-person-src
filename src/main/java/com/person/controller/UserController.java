package com.person.controller;

import com.google.gson.Gson;
import com.person.bean.Collect;
import com.person.bean.Feedback;
import com.person.bean.LayuiData;
import com.person.bean.User;
import com.person.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
//用户登录
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

//用户注册
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
        String num=userService.checkSex(sex);
        System.out.println(num);
        String user = userService.checkAccount(account);
        if (user!= null) {
            return "该账号已经存在！";
        } else {
            userService.addUser(account,password,name,num,age,phone,address);
            return "注册成功";

        }
    }

//用户求职反馈表
    @RequestMapping("/getFeedback")
    @ResponseBody
    public String getFeedback(HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userid= user.getId();
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String industry= request.getParameter("industry");
        System.out.println(industry);
        String post= request.getParameter("post");
        System.out.println(post);
        HashMap<String,Object> condition=new HashMap<>();
        Integer startL=(Integer.valueOf(page)-1)*Integer.valueOf(limit);
        Integer endL=Integer.valueOf(page)*Integer.valueOf(limit);
        condition.put("startL",startL);
        condition.put("endL",endL);
        if (startTime!=null&&!"".equals(startTime)){
            condition.put("startTime",startTime);
        }if (endTime!=null&&!"".equals(endTime)){
            condition.put("endTime",endTime);
        }if (industry!=null&&!"".equals(industry)){
            condition.put("industry",industry);
        }if (post!=null&&!"".equals(post)){
            condition.put("post",post);
        }
        List<Feedback> list=userService.getFeedback(String.valueOf(userid),condition);
        Integer count=userService.findCount(String.valueOf(userid),condition);
        LayuiData layuiData=new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return new Gson().toJson(layuiData);



    }


//用户收藏表
    @RequestMapping("/getCollect")
    @ResponseBody
    public String getCollect(HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userid= user.getId();
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String industry= request.getParameter("industry");
        System.out.println(industry);
        String post= request.getParameter("post");
        System.out.println(post);
        HashMap<String,Object> condition=new HashMap<>();
        Integer startL=(Integer.valueOf(page)-1)*Integer.valueOf(limit);
        Integer endL=Integer.valueOf(page)*Integer.valueOf(limit);
        condition.put("startL",startL);
        condition.put("endL",endL);
        if (startTime!=null&&!"".equals(startTime)){
            condition.put("startTime",startTime);
        }if (endTime!=null&&!"".equals(endTime)){
            condition.put("endTime",endTime);
        }if (industry!=null&&!"".equals(industry)){
            condition.put("industry",industry);
        }if (post!=null&&!"".equals(post)){
            condition.put("post",post);
        }
        List<Collect> list=userService.getCollect("1",condition);
        Integer count=userService.findCount2("1",condition);
        LayuiData layuiData=new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return new Gson().toJson(layuiData);

    }

}
