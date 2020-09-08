package com.person.controller;

import com.google.gson.Gson;
import com.person.bean.LayuiData;
import com.person.bean.Menu;
import com.person.bean.Params;
import com.person.bean.User;
import com.person.service.KnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/person")
public class ZhiShiKuController {

    @Autowired
    KnowService knowService;

    @GetMapping(value = "/getZhishi")
    public String hello() {
        return "ZhishiKu";
    }

    @GetMapping(value = "/getKnowMsg")
    @ResponseBody
    public Object getKnowMsg(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        Integer page1 = Integer.parseInt(page);
        page1 = (page1 - 1) * limit;
        String title=request.getParameter("title");
        String scope=request.getParameter("scope");

        List<Menu> list=knowService.getKnowMsg(page1,limit,title,scope);
        Integer count=knowService.findCount();
        LayuiData<User> layuiData = new LayuiData<User>();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return new Gson().toJson(layuiData);
    }

    @GetMapping(value = "/addselect")
    @ResponseBody
    public Object addselect(){
        List<Params> list=knowService.addselect();
        return new Gson().toJson(list);
    }
}
