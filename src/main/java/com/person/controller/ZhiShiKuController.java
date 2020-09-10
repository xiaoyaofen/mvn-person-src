package com.person.controller;

import com.google.gson.Gson;
import com.person.bean.*;
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
        Integer count=knowService.findCount(title,scope);
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

    @GetMapping(value = "/delKnow")
    @ResponseBody
    public Object delKnow(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        Integer num=knowService.delKnow(id);
        if (num!=0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @GetMapping(value = "/addKnow")
    @ResponseBody
    public Object addKnow(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("hidename");
        String scope=request.getParameter("lingyu");
        String menuname=request.getParameter("newname");
        String scopeId=knowService.findScope(scope);
        Integer num=knowService.addKnow(id,menuname,scopeId);
        if (num!=0){
            return "新增成功";
        }else {
            return "新增失败，请重试";
        }
    }

    @GetMapping(value = "/fixKnow")
    @ResponseBody
    public Object fixKnow(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("hideid");
        String scope=request.getParameter("lingyu");
        String menuname=request.getParameter("newname");
        String scopeId=String.valueOf(knowService.findScope(scope));
        Integer num=knowService.fixKnow(id,scopeId,menuname);
        if (num!=0){
            return "修改成功";
        }else {
            return "修改失败，请重试";
        }
    }

    @GetMapping(value = "/addZhishi")
    @ResponseBody
    public Object addZhishi(HttpServletRequest request, HttpServletResponse response) {
        String menuname = request.getParameter("newname");
        String scope = request.getParameter("scope");
        String detial = request.getParameter("detial");
        String scopeId = String.valueOf(knowService.findScope(scope));
        Menu menu = knowService.findCourse(scopeId, menuname);
        if (menu != null) {
            return "该领域此知识库已经存在！";
        } else {
            Params params = knowService.findScopeParmas(scope);
            if (params == null) {
                String maxValue = knowService.findMaxValue();
                Integer maxValue1 = Integer.parseInt(maxValue) + 1;
                Integer num = knowService.addScopeParam(scope, String.valueOf(maxValue1));
                if (num != 0) {
                    String scopeId1 = String.valueOf(knowService.findScope(scope));
                    Integer num1 = knowService.addKnowmenu(menuname, detial, scopeId1);
                    if (num1 != 0) {
                        return "新增知识库成功";
                    } else {
                        return "新增知识库失败，请重试！";
                    }
                }
            }
        }
        return null;
    }

    @GetMapping(value = "/Charpter")
    public String showCharpter() {
        return "Charpter";
    }

    @GetMapping(value = "/getCharpter")
    @ResponseBody
    public Object getCharpter(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        Integer page1 = Integer.parseInt(page);
        page1 = (page1 - 1) * limit;
        String title=request.getParameter("title");
        String scope=request.getParameter("scope");
        return new Gson().toJson(knowService.findCharpter(page1,limit,title,scope));
    }

    @GetMapping(value = "/delCharpter")
    @ResponseBody
    public Object delCharpter(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        String resp=knowService.delCharpter(id);
        return resp;
    }

    @GetMapping(value = "/seeCharpter")
    @ResponseBody
    public Object seeCharpter(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        Charpter charpter=knowService.seeCharpter(id);
        if (charpter.getUrl()==null||charpter.getUrl().equals("")){
            return "本章节未匹配内容，请先新增！";
        }else {
            return new Gson().toJson(charpter);
        }
    }
}
