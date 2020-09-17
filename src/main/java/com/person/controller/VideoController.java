package com.person.controller;

import com.person.bean.Menu;
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
    public String Video(Model model) {
        List<Menu> list=videoService.findVideo("1");
        model.addAttribute("list",list);
        return "Video1";
    }

    @RequestMapping(value = "/watchVideo/{url}")
    public Object watchVideo(@PathVariable(value = "url")String url,Model model){
        model.addAttribute("url",url);
        return "WatchVideo";
    }

}
