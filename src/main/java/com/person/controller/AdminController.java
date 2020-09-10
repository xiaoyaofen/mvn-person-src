package com.person.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.person.bean.LayuiData;
import com.person.bean.Station;
import com.person.bean.User;
import com.person.bean.TreeNode;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String adminRole() {
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

    //批量添加学生============================
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

        List<List<Object>> list = new ArrayList<>();
        List<User> userInfoList = new ArrayList<>();
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

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

                User userInfo = new User();
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setAccount(row.getCell(0).getStringCellValue());
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setSex(Integer.parseInt(row.getCell(1).getStringCellValue()));
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setAge(Integer.parseInt(row.getCell(2).getStringCellValue()));
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setTel(row.getCell(3).getStringCellValue());
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setAddress(row.getCell(4).getStringCellValue());
                userInfoList.add(userInfo);
            }
        }
        workbook.close();
        inputStream.close();
        Integer res = adminService.uploadExcel(userInfoList);
        LayuiData layuiData = new LayuiData();
        layuiData.setCode(0);
        layuiData.setMsg("success");
        return JSON.toJSONString(layuiData);
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


    //高校人才推荐 ==============工作信息界面绘制
    @RequestMapping(value = "recommendFrame", produces = "text/plain;charset=utf-8")
    public String userRecommendFrame(){
        return "user-recommend";
    }

    //高校人才推荐 ==============工作信息数据获取
    @RequestMapping(value = "recommend", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String userRecommend(String station,String industry,Integer limit,Integer page){
        HashMap<String,Object> condition = new HashMap<>();
        System.out.println(station);
        System.out.println(industry);
        if(station!= null && !"".equalsIgnoreCase(station)){
            condition.put("station",station);
        }
        if(industry!= null && !"".equalsIgnoreCase(industry)){
            condition.put("industry",industry);
        }
        LayuiData<Station> layuiData =  adminService.userRecommend(condition,limit,page);
        return JSON.toJSONString(layuiData);
    }

    //高校人才推荐 ==============选择人才表格显示
    @RequestMapping(value = "userSelectFrame", produces = "text/plain;charset=utf-8")
    public String userSelectFrame(){
        return "user-select";
    }

    //高校人才推荐 ==============选择人才数据显示
    @RequestMapping(value = "userSelect", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String userSelect(String beginDate, String endDate, String schoolName , String specialty, Integer limit, Integer page){

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
        LayuiData<User> layuiData = adminService.userSelect(condition,limit,page);

        return JSON.toJSONString(layuiData);
    }

    //高校人才推荐 ==============确定选择推荐人选
    @RequestMapping(value = "userSelectSure", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String userSelectSure(HttpServletRequest request,String list){
        Integer jobid  = (Integer)request.getSession().getAttribute("idOfJob");
        Gson gson = new Gson();
        List<User> idList = gson.fromJson(list, new TypeToken<ArrayList<User>>() {}.getType());

        for(User u : idList){
            System.out.println(u.getId());
        }
        System.out.println(list);
//        List<Integer> list = new ArrayList<>();
//        if(list.size()!=0){
//            System.out.println(JSON.toJSONString(list));
//        }
//        Integer res = adminService.userSelectSure(list,jobid);
        return list.toString();
    }
    @RequestMapping(value = "/editRightShow/{roleId}", produces = "text/plain;charset=utf-8")
    public String editRightShow(@PathVariable(value = "roleId") String roleId,Model model) {

        model.addAttribute("roleId",roleId);
        return "right-edit";
    }

    @RequestMapping(value = "/findRight/{roleId}", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findRight(@PathVariable(value = "roleId") String roleId) {

        TreeNode treeNode = adminService.findRight(Integer.parseInt(roleId));
        return JSON.toJSONString(treeNode);
    }

    @RequestMapping(value = "/editRight", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String editRight(String permList,String roleId){

        Gson gson = new Gson();
        List<String> idList = gson.fromJson(permList, new TypeToken<ArrayList<String>>() {}.getType());
        System.out.println(idList);
        return roleId;
    }

}
