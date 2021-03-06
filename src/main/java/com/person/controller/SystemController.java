package com.person.controller;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.person.bean.*;
import com.person.service.AdminService;
import com.person.service.SystemService;
import com.person.util.SmsSenderUtil;
import com.person.util.VerifyUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/paramsView", produces = "text/plain;charset=utf-8")
    public String paramsView(String name, String type) {
        return "parameterManagement";
    }

    @RequestMapping(value = "/paramsList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String paramsList(HttpServletRequest request, HttpServletResponse response) {
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String name = request.getParameter("key[paramsName]");
        String type = request.getParameter("key[paramsType]");
        Integer page = Integer.parseInt(pageStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        LayuiData layuiData = systemService.getParamList(page, pageSize, name, type);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/paramsType", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String paramsType() {

        List<String> list = systemService.paramsTypeList();

        return JSON.toJSONString(list);

    }

    @RequestMapping(value = "/paramsAddView", produces = "text/plain;charset=utf-8")
    public String paramsAddView() {

        return "params-add";
    }


    @RequestMapping(value = "/addParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String addParams(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("paramsName");//页码
        String type = request.getParameter("paramsType");//页码
        String value = request.getParameter("paramsValue");//页码

        Boolean flag = systemService.addParams(name, type, value);
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/delParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delParams(String id, String state) {
        Boolean flag = systemService.delParams(state, Integer.parseInt(id));
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }


    @RequestMapping(value = "/editParamsView", produces = "text/plain;charset=utf-8")
    public String editParamsView() {
        return "params-edit";
    }

    @RequestMapping(value = "/checkParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String checkParams(String paramsName, String paramsType) {
        String num = "1";
        Params params = systemService.checkParams(paramsName, paramsType);
        if (params == null) {
            num = "2";
        }

        return "num";
    }

    @RequestMapping(value = "/editParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String editParams(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String name = request.getParameter("paramsName");
        Boolean flag = systemService.editParams(name, Integer.parseInt(id));
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }


    @RequestMapping(value = "/rightManagerView", produces = "text/plain;charset=utf-8")
    public String rightManagerView() {
        return "rightManager";
    }


    @RequestMapping(value = "/roleList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String roleList(HttpServletRequest request, HttpServletResponse response) {

        LayuiData layuiData = systemService.roleList();

        return JSON.toJSONString(layuiData);

    }

    @RequestMapping(value = "/editRightShow/{roleId}", produces = "text/plain;charset=utf-8")
    public String editRightShow(@PathVariable(value = "roleId") String roleId, Model model) {

        model.addAttribute("roleId", roleId);
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
    public String editRight(String permList, String roleId) {


        Gson gson = new Gson();
        List<String> idList = gson.fromJson(permList, new TypeToken<ArrayList<String>>() {
        }.getType());
        Boolean flag = systemService.editRight(Integer.parseInt(roleId), idList);
        if (flag) {
            return "success";
        } else {
            return "failed";
        }

    }

    @RequestMapping(value = "/adminLogView", produces = "text/plain;charset=utf-8")
    public String adminLogView() {

        return "adminLog";
    }

    @RequestMapping(value = "/adminLogList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String adminLogList(HttpServletRequest request) {
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String startTime = request.getParameter("key[startTime]");
        String endTime = request.getParameter("key[endTime]");
        Integer page = Integer.parseInt(pageStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        LayuiData layuiData = systemService.adminLog(page, pageSize, startTime, endTime);
        return JSON.toJSONString(layuiData);

    }


    @RequestMapping(value = "/userLogView", produces = "text/plain;charset=utf-8")
    public String userLogView() {

        return "userLog";
    }

    @RequestMapping(value = "/userLogList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String userLogList(HttpServletRequest request) {
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String startTime = request.getParameter("key[startTime]");
        String endTime = request.getParameter("key[endTime]");

        Integer page = Integer.parseInt(pageStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        LayuiData layuiData = systemService.userLog(page, pageSize, startTime, endTime);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/userManagerListView", produces = "text/plain;charset=utf-8")
    public String userManagerListView() {

        return "userManager";
    }


    //用户管理列表
    @RequestMapping(value = "/userManagerList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String userManagerList(HttpServletRequest request) {
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String school = request.getParameter("key[school]");
        String state = request.getParameter("key[state]");
        Integer page = Integer.parseInt(pageStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        LayuiData layuiData = systemService.userList(page, pageSize, school, state);
        return JSON.toJSONString(layuiData);
    }

    //禁用/启用用户
    @RequestMapping(value = "/controllerUser", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String controllerUser(String id, String state) {

        Boolean flag = systemService.userManager(Integer.parseInt(id), state);
        if (flag) {
            return "success";
        } else {
            return "failed";
        }
    }


    //管理员登录界面
    @RequestMapping(value = "/adminLogInView", produces = "text/plain;charset=utf-8")
    public String adminLogInView() {

        return "adminLogin";

    }

    //管理员登录
    @RequestMapping(value = "/adminLogIn", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String adminLogIn(HttpServletRequest req, String account, String pwd, String code, String num) {
        if (code.equalsIgnoreCase(num)) {
            Admin admin = systemService.adminLogIn(account, pwd);
            if (admin != null) {
                req.getSession().setAttribute("admin",admin);
                return "登录成功";
            } else {
                return "账号或密码错误";
            }
        } else {
            return "验证码错误";
        }
    }

    @RequestMapping(value = "/adminRegisterView", produces = "text/plain;charset=utf-8")
    public String adminRegisterView() {

        return "adminRegister";
    }


    @RequestMapping(value = "/findRole", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findRole() {

        List<Role> list = systemService.findRole();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
        }

        return JSON.toJSONString(list);

    }

    @RequestMapping(value = "/upload", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public Object upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        try {
            //获取文件名
            String originalName = file.getOriginalFilename();
            //扩展名
            String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
            System.out.println("prefix" + prefix);
            //使用UUID+后缀名保存文件名，防止中文乱码问题
            String uuid = UUID.randomUUID() + "";
            File file1 = new File("");
            String filePath = file1.getCanonicalPath() +
                    File.separator + "src" + File.separator + "main" + File.separator + "resources"
                    + File.separator + "static" + File.separator  + "qualification";
            System.out.println("projectPath==" + filePath);
            String projectPath = filePath + File.separator + uuid + "." + prefix;
            System.out.println(projectPath);
            File files = new File(projectPath);
            file.transferTo(files); // 将接收的文件保存到指定文件中
            String url = "\\qualification" + File.separator + uuid + "." + prefix;
            LayuiData layuiData = new LayuiData();
            layuiData.setCode(0);
            layuiData.setMsg(url);
            return JSON.toJSONString(layuiData);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/register", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String register(HttpServletRequest request) {
        String roleId = request.getParameter("roleId");
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        String address = request.getParameter("address");
        String unit = request.getParameter("unit");
        String qualification = request.getParameter("qualification");
        String resMsg=null;
        Admin admin = systemService.checkAccount(account);
        if (admin != null) {
            resMsg= "1";
        }
        else{
            Long newTime = new Date().getTime();
            System.out.println(newTime);
            HttpSession session = request.getSession();
            Long currentLong = (Long) session.getAttribute("currentLong");
            System.out.println(currentLong);
            if(newTime - currentLong > (120 * 1000)){
                resMsg="4" ;
            }else{
                String verifyCode = (String) session.getAttribute("verifyCode");
                if(code.equalsIgnoreCase(verifyCode)){
                    Boolean flag = systemService.adminRegister(roleId, account, name, password, phone, address, unit, qualification);
                    if (flag) {
                        resMsg= "2";
                    } else {
                        resMsg= "3";
                    }
                }else{
                    resMsg="5";
                }
            }
        }
//        else {
//            Boolean flag = systemService.adminRegister(roleId, account, name, password, phone, address, unit, qualification);
//            if (flag) {
//                return "2";
//            } else {
//                return "3";
//            }
//        }
        return resMsg;

    }

    @RequestMapping(value = "/jobTradeView", produces = "text/plain;charset=utf-8")
    public String jobTradeView() {
        return "jobTrade";
    }




    @RequestMapping(value = "/tradeList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String tradeList() {
        List<Params> list = systemService.selectTrade();
        return JSON.toJSONString(list);
    }




    @RequestMapping(value = "/jodTradeList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String jodTradeList(HttpServletRequest request){
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String trade = request.getParameter("key[trade]");
        Integer page = Integer.parseInt(pageStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        LayuiData layuiData = systemService.postManager(trade,page,pageSize);
        return JSON.toJSONString(layuiData);
    }


    @RequestMapping(value = "/delJob", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delJob(String id,String state){

        Boolean flag = systemService.delJob(Integer.parseInt(id),state);
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/addJob", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public  String addJob(String job,String addTrade){

        String str=systemService.addJob(job,Integer.parseInt(addTrade));
        return str;
    }



    @RequestMapping(value = "/editJob", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public  String editJob(String job1,String editTrade,String id){
        String str=systemService.editJob(job1,Integer.parseInt(editTrade),Integer.parseInt(id));
        return str;

    }


    @RequestMapping(value = "/checkCompanyView", produces = "text/plain;charset=utf-8")
    public String checkCompanyView(){
        return "checkCompany";
    }

    @RequestMapping(value = "/checkCompanyList", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String checkCompanyList(HttpServletRequest request){
        String pageStr = request.getParameter("page");//页码
        String pageSizeStr = request.getParameter("limit");//每页记录数
        String companyName = request.getParameter("key[companyName]");
        String state =request.getParameter("key[state]");
        Integer page = Integer.parseInt(pageStr);
        Integer pageSize = Integer.parseInt(pageSizeStr);
        LayuiData layuiData = systemService.checkCompanyList(page,pageSize,companyName,state);
        return JSON.toJSONString(layuiData);
    }

    @RequestMapping(value = "/checkCompany", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String checkCompany(String id,String state){
        Boolean flag=systemService.checkCompany(state,Integer.parseInt(id));
        String str=null;
        if(flag){
            str="success";
        }else{
            str="fail";
        }
        return str;
    }

    @RequestMapping(value = "/adminLogout", produces = "text/plain;charset=utf-8")
    public String logout(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "adminLogin";
    }


    @RequestMapping(value = "/forgetPwdView", produces = "text/plain;charset=utf-8")
    public String forgetPwdView() {
        return "forgetPwd";
    }


    @RequestMapping(value = "/getCode", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String getCode(String phone, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long currentLong = (Long) session.getAttribute("currentLong");
        String code = (String) session.getAttribute("verifyCode");
        String resMsg = null;
        if (currentLong == null) {
            code = VerifyUtil.createCode();
            currentLong = new Date().getTime();
            session.setAttribute("verifyCode", code);
            session.setAttribute("currentLong", currentLong);
            boolean sendFlag = SmsSenderUtil.sender(phone,code);
            if (sendFlag) {
                resMsg = "1";
            } else {
                resMsg = "2";
            }

        } else {
            Long newTime = new Date().getTime();
            if (newTime - currentLong > (60 * 1000)) {
                code = VerifyUtil.createCode();
                session.setAttribute("currentLong", newTime);
                boolean sendFlag = SmsSenderUtil.sender(phone,code);
                if (sendFlag) {
                    resMsg = "1";
                } else {
                    resMsg = "2";
                }
            }
        }
        return resMsg;
    }


    @RequestMapping(value = "/resetPassword", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String resetPassword(HttpServletRequest request) {
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");
        String password = request.getParameter("password");
        Long newTime = new Date().getTime();
        HttpSession session = request.getSession();
        Long currentLong = (Long) session.getAttribute("currentLong");
        String marks = null;
        if (newTime - currentLong > (120 * 1000)) {
            marks = "4";
        } else {
            String verifyCode = (String) session.getAttribute("verifyCode");
            if (code.equalsIgnoreCase(verifyCode)) {
                marks = systemService.resetPassword(password, phone);
            } else {
                marks = "5";
            }
        }
        return marks;
    }

    @RequestMapping(value = "/getCodes", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String getCodes(String phone, HttpServletRequest request) {
        System.out.println(phone);
        Admin admin = systemService.findAdminByPhone(phone);
        String resMsg = null;
        if (admin != null) {
            resMsg = "3";
        } else {
            HttpSession session = request.getSession();
            Long currentLong = (Long) session.getAttribute("currentLong");
            String code = (String)session.getAttribute("verifyCode");
            if(currentLong == null){
                code = VerifyUtil.createCode();
                currentLong = new Date().getTime();
                session.setAttribute("verifyCode", code);
                session.setAttribute("currentLong", currentLong);
                boolean sendFlag = SmsSenderUtil.sender(phone,code);
                if(sendFlag){
                    resMsg= "1";
                }else{
                    resMsg= "2";
                }

            }else{
                Long newTime = new Date().getTime();
                if (newTime - currentLong > (60 * 1000)) {
                    code = VerifyUtil.createCode();
                    session.setAttribute("currentLong", newTime);
                    boolean sendFlag = SmsSenderUtil.sender(phone,code);
                    if(sendFlag){
                        resMsg= "1";
                    }else{
                        resMsg= "2";
                    }
                }
            }
        }

        return resMsg;
    }



}
