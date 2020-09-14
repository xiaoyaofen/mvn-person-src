package com.person.controller;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.person.bean.LayuiData;
import com.person.bean.Params;
import com.person.bean.TreeNode;
import com.person.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @RequestMapping(value = "/paramsView", produces = "text/plain;charset=utf-8")
    public String paramsView(String name,String type){
        return "parameterManagement";
    }

    @RequestMapping(value = "/paramsList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String paramsList(HttpServletRequest request, HttpServletResponse response){
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String name=request.getParameter("key[paramsName]");
        String type=request.getParameter("key[paramsType]");
        Integer page=Integer.parseInt(pageStr);
        Integer pageSize=Integer.parseInt(pageSizeStr);
        LayuiData layuiData=systemService.getParamList(page,pageSize,name,type);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/paramsType", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String paramsType(){

        List<String> list=systemService.paramsTypeList();

        return JSON.toJSONString(list);

    }

    @RequestMapping(value = "/paramsAddView", produces = "text/plain;charset=utf-8")
    public String paramsAddView(){

        return "params-add";
    }


    @RequestMapping(value = "/addParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String addParams(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("paramsName");//页码
        String type = request.getParameter("paramsType");//页码
        String value = request.getParameter("paramsValue");//页码

        Boolean flag=systemService.addParams(name,type,value);
        if(flag){
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping(value = "/delParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delParams(String id,String state){
        Boolean flag=systemService.delParams(state,Integer.parseInt(id));
        if(flag){
            return "success";
        }else{
            return "fail";
        }
    }


    @RequestMapping(value = "/editParamsView", produces = "text/plain;charset=utf-8")
    public String editParamsView(){
        return "params-edit";
    }

    @RequestMapping(value = "/checkParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String checkParams(String paramsName,String paramsType){
         String num="1";
        Params params=systemService.checkParams(paramsName,paramsType);
        if(params==null){
            num="2";
        }

        return "num";
    }

    @RequestMapping(value = "/editParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String editParams(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        String name=request.getParameter("paramsName");
        Boolean flag=systemService.editParams(name,Integer.parseInt(id));
        if(flag){
            return "success";
        }else{
            return "fail";
        }
    }


    @RequestMapping(value = "/rightManagerView", produces = "text/plain;charset=utf-8")
    public String rightManagerView(){
        return "rightManager";
    }


    @RequestMapping(value = "/roleList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String roleList(HttpServletRequest request, HttpServletResponse response){

        LayuiData layuiData =systemService.roleList();

        return JSON.toJSONString(layuiData);

    }

    @RequestMapping(value = "/editRightShow/{roleId}", produces = "text/plain;charset=utf-8")
    public String editRightShow(@PathVariable(value = "roleId") String roleId, Model model) {

        model.addAttribute("roleId",roleId);
        return "right-edit";
    }

    @RequestMapping(value = "/findRight/{roleId}", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findRight(@PathVariable(value = "roleId") String roleId) {

        TreeNode treeNode = systemService.findRight(Integer.parseInt(roleId));
        return JSON.toJSONString(treeNode);
    }


    @RequestMapping(value = "/editRight", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String editRight(String permList,String roleId){
        System.out.println(roleId);

        Gson gson = new Gson();
        List<String> idList = gson.fromJson(permList, new TypeToken<ArrayList<String>>() {}.getType());
          Boolean flag=systemService.editRight(Integer.parseInt(roleId),idList);
          if(flag){
              return "success";
          }else{
              return "failed";
          }

    }

    @RequestMapping(value = "/adminLogView", produces = "text/plain;charset=utf-8")
    public String adminLogView(){

        return "adminLog";
    }

    @RequestMapping(value = "/adminLogList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String adminLogList(HttpServletRequest request){
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String startTime=request.getParameter("key[startTime]");
        String endTime=request.getParameter("key[endTime]");
        Integer page=Integer.parseInt(pageStr);
        Integer pageSize=Integer.parseInt(pageSizeStr);
        LayuiData layuiData=systemService.adminLog(page,pageSize,startTime,endTime);
        return JSON.toJSONString(layuiData);

    }


    @RequestMapping(value = "/userLogView", produces = "text/plain;charset=utf-8")
    public String userLogView(){

        return "userLog";
    }

    @RequestMapping(value = "/userLogList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String userLogList(HttpServletRequest request){
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String startTime=request.getParameter("key[startTime]");
        String endTime=request.getParameter("key[endTime]");

        Integer page=Integer.parseInt(pageStr);
        Integer pageSize=Integer.parseInt(pageSizeStr);
        LayuiData layuiData=systemService.userLog(page,pageSize,startTime,endTime);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/userManagerListView", produces = "text/plain;charset=utf-8")
    public String userManagerListView(){

        return "userManager";
    }



    //用户管理列表
    @RequestMapping(value = "/userManagerList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String userManagerList(HttpServletRequest request){
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String school=request.getParameter("key[school]");
        String state=request.getParameter("key[state]");
        Integer page=Integer.parseInt(pageStr);
        Integer pageSize=Integer.parseInt(pageSizeStr);
        LayuiData layuiData=systemService.userList(page,pageSize,school,state);
        return JSON.toJSONString(layuiData);
    }

    //禁用/启用用户
    @RequestMapping(value = "/controllerUser", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String controllerUser(String id,String state){

       Boolean flag=systemService.userManager(Integer.parseInt(id),state);
        if(flag){
            return "success";
        }else{
            return "failed";
        }
    }

}
