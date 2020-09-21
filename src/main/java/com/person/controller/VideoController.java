package com.person.controller;

import com.person.bean.*;
import com.person.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/person")
public class VideoController {

    @Autowired
    VideoService videoService;

    @GetMapping(value = "/Video")
    public String Video(Model model,HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        String id=request.getParameter("id");
        if (user!=null){
            Product product=videoService.hasStudy(String.valueOf(user.getId()),id);
            if (null==product){
                videoService.addStudy(String.valueOf(user.getId()),id);
            }else {
                videoService.updateStudyTime(String.valueOf(user.getId()),id);
            }
        }
        List<Menu> list=videoService.findVideo(id);
        Product product=videoService.findProductOne(id);
        model.addAttribute("product",product);
        model.addAttribute("list",list);
        return "Video1";
    }

    @GetMapping(value = "/WatchVideo/{id}")
    public Object WatchVideo(@PathVariable(value = "id")String id,Model model,HttpServletRequest request, HttpServletResponse response){
        String url=videoService.findUrlById(id);
        url="/X-admin"+url;
        Charpter charpter=videoService.findCharpterByid(id);
        model.addAttribute("charpter",charpter);
        model.addAttribute("url",url);
        return "WatchVideo";
    }

    @GetMapping(value = "/seeVideo")
    public String seeVideo(Model model) {
        List<Product> list=videoService.findProduct();
        model.addAttribute("list",list);
        return "Video";
    }

    @GetMapping(value = "/talk")
    public String talk(Model model) {
        List<Talk> list=videoService.findTalk();
        Integer count=videoService.findTalkCount();
        model.addAttribute("list",list);
        model.addAttribute("count",count);
        return "Talk";
    }

    @GetMapping(value = "/addMessage")
    @ResponseBody
    public Object addMessage(Model model,HttpServletRequest request, HttpServletResponse response) {
        String message=request.getParameter("message");
        User user= (User) request.getSession().getAttribute("user");
        if (user!=null){
            Integer num=videoService.addMessage(message,String.valueOf(user.getId()));
            if (num!=0){
                return "发表成功";
            }else {
                return "发表失败，请重试！";
            }
        }else {
            return "请先登录才能发表评论";
        }

    }
}
