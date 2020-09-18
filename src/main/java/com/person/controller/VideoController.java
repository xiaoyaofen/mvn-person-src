package com.person.controller;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Product;
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
        String id=request.getParameter("id");
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

}
