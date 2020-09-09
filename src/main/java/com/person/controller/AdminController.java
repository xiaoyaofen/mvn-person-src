package com.person.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.person.bean.LayuiData;
import com.person.bean.UserInfo;
import com.person.service.AdminService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("getUserFrame")
    public String adminRole(){
        return "user-show";
    };

    @RequestMapping("getUser")
    @ResponseBody
    public String getUserByAdmin(String beginDate, String endDate, String schoolName , String specialty, Integer limit, Integer page){
        HashMap<String,Object> condition = new HashMap<>();
        System.out.println(beginDate);
        System.out.println(endDate);
        if(beginDate!=null && !"".equalsIgnoreCase(beginDate)){
            condition.put("beginDate",beginDate);
        }
        if(endDate!=null && !"".equalsIgnoreCase(endDate)){
            condition.put("endDate",endDate);
        }
        if(schoolName!=null && !"".equalsIgnoreCase(schoolName)){
            condition.put("schoolName",schoolName);
        }
        if(specialty!=null && !"".equalsIgnoreCase(specialty)){
            condition.put("specialty",specialty);
        }
        if(limit == null){
            limit = 5;
        }
        if(page == null){
            page = 1;
        }
        LayuiData<UserInfo> pageBean = adminService.getUserByAdmin(condition,limit,page);
        return new Gson().toJson(pageBean);
    }

    @RequestMapping("uploadFrame")
    public String adminUploadFrame(){
        return "user-adds";
    }

    @RequestMapping( "upload")
    @ResponseBody
    public String uploadExcel(HttpServletRequest request, MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new Exception("请上传excel文件！");
        }
        if (null == workbook) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
//                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
//                    System.out.println(y);
//                    cell = row.getCell(y);
//                    li.add(cell);
//                }
                UserInfo userInfo = new UserInfo();
                userInfo.setAccount(row.getCell(0).getStringCellValue());
                userInfo.setPwd("123456");
                
                list.add(userInfo);
            }
        }
        workbook.close();
//        List<List<Object>> list = adminService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();

        for (int i = 0; i < list.size(); i++) {
            UserInfo lo = list.get(i);
            //TODO 随意发挥
            System.out.println(lo);

        }
        return "上传成功";
    }

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
        LayuiData layuiData=adminService.getParamList(page,pageSize,name,type);
        return JSON.toJSONString(layuiData);
    }


    @RequestMapping(value = "/paramsType", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String paramsType(){

        List<String> list=adminService.paramsTypeList();

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

        Boolean flag=adminService.addParams(name,type,value);
        if(flag){
            return "success";
        }else{
            return "fail";
        }
    }

    @RequestMapping(value = "/delParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String delParams(String id,String state){
        Boolean flag=adminService.delParams(state,Integer.parseInt(id));
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

    @RequestMapping(value = "/editParams", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String editParams(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        String name=request.getParameter("paramsName");
        System.out.println(name+"name");
        System.out.println(id+"id");
        Boolean flag=adminService.editParams(name,Integer.parseInt(id));
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

        LayuiData layuiData =adminService.roleList();

        return JSON.toJSONString(layuiData);

    }


}
