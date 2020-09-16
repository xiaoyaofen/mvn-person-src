package com.person.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.person.bean.*;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.person.bean.Station;



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
        LayuiData<User> pageBean = adminService.getUserByAdmin(condition,limit,page);
        return new Gson().toJson(pageBean);
    }

    @RequestMapping("uploadFrame")
    public String adminUploadFrame(){
        return "user-adds";
    }

    //批量添加学生============================
    @RequestMapping( "upload")
    @ResponseBody
    public String uploadExcel(HttpServletRequest request,MultipartFile file) throws Exception {
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

        List<User> userInfoList = new ArrayList<>();
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            System.out.println("============"+workbook.getNumberOfSheets());
            sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                System.out.println("========================"+sheet.getLastRowNum());
                row = sheet.getRow(j);

                if ( row == null || row.getFirstCellNum() == j ||row.equals("")) {
                    continue;
                }

                User userInfo = new User();
                System.out.println(row.getCell(0));
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
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setSchool(row.getCell(5).getStringCellValue());
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setMajor(row.getCell(6).getStringCellValue());
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                userInfo.setName(row.getCell(7).getStringCellValue());
                System.out.println(userInfo);
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
        LayuiData<Station> layuiData = adminService.userRecommend(condition, limit, page);
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
        if(jobid==null){
            jobid=1;
        }
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = builder.create();
        List<User> list1 = gson.fromJson(list, new TypeToken<List<User>>() {}.getType());
        List<Integer> l = new ArrayList<>();
        if(list1.size()<=0){
            return 0+"";
        }
        for(int i = 0; i < list1.size() ; i++) {
            User p = list1.get(i);
            l.add(p.getId());
        }
        Integer res = adminService.userSelectSure(l,jobid);
        return ""+res;
    }

    //公司招聘管理=================显示界面
    @RequestMapping(value = "adminRecruitFrame", produces = "text/plain;charset=utf-8")
    public String adminRecruitFrame(){
        return "admin-recruit";
    }

    //公司招聘管理=================管理界面数据获取
    @ResponseBody
    @RequestMapping(value = "adminRecruit", produces = "text/plain;charset=utf-8")
    public String adminRecruit(HttpServletRequest request,Integer limit,Integer page,String jobName1,Integer trade1,Integer industry1,Integer education1){
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        HashMap<String,Object> condition = new HashMap<>();
        Integer adminId = 1;
        if(admin != null){
            adminId = admin.getId();
        }
        if(jobName1 != null && !"".equalsIgnoreCase(jobName1)){
           condition.put("title",jobName1);
        }
        if(trade1 != null){
            condition.put("trade",trade1);
        }
        if(industry1 != null){
            condition.put("industry",industry1);
        }
        if(education1 != null ){
            condition.put("education",education1);
        }
        if(limit== null){
            limit = 5;
        }
        if(page== null){
            page = 1;
        }
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        LayuiData<Station> layuiData = adminService.adminRecruit(adminId,limit,page,condition);
        return gson.toJson(layuiData);
    }


    //公司招聘管理=================招聘修改界面
    @RequestMapping(value = "recruitUpdateFrame", produces = "text/plain;charset=utf-8")
    public String recruitUpdateFrame(){
        return "recruit-update";
    }

    //公司招聘管理=================招聘修改
    @RequestMapping(value = "recruitUpdate", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String recruitUpdate(HttpServletRequest request,Station station){
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        Integer adminId = 1;
        if(admin != null){ adminId = admin.getId(); }
        Integer res = adminService.recruitUpdate(station,adminId);
        return ""+res;
    }

    //公司招聘管理=================招聘新增界面
    @RequestMapping(value = "recruitInsertFrame", produces = "text/plain;charset=utf-8")
    public String recruitInsertFrame(Integer id){
        return "recruit-add";
    }

    //公司招聘管理=================招聘新增
    @RequestMapping(value = "recruitInsert", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String recruitInsert(HttpServletRequest request,Station station){
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        Integer adminId = 1;
        if(admin != null){ adminId = admin.getId(); }
        Integer res = adminService.recruitInsert(station,adminId);
        return ""+res;
    }



    //求职管理====================界面显示
    @RequestMapping(value = "adminBioCheckFrame", produces = "text/plain;charset=utf-8")
    public String adminBioCheckFrame(){
        return "admin-bio-check";
    }


    //求职管理====================界面显示
    @RequestMapping(value = "adminBioCheck", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String adminBioCheck(HttpServletRequest request,Integer limit,Integer page,Integer education,Integer major,String title){
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        Integer adminId = 1;
        if(admin != null){
            adminId = admin.getId();
        }

        HashMap<String,Object> condition = new HashMap<>();
        if(education != null){
            condition.put("education",education);
        }
        if(major != null){
            condition.put("major",major);
        }
        if(title != null){
            condition.put("title",title);
        }

        LayuiData<User> layuiData = adminService.adminBioCheck(limit,page,condition,adminId);

        return new Gson().toJson(layuiData);
    }



    //获取下拉菜单的数据
    @RequestMapping(value = "getOptionData", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String getOptionData(String type){
        System.out.println("================="+type);
        LayuiData<Params> paramsList = adminService.getOptionData(type);
        return new Gson().toJson(paramsList);
    }

    //获取统计新增简历信息
    @RequestMapping(value = "getConutData", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String getConutData(){
        return new Gson().toJson(adminService.getConutData());
    }

    //求职进度表===============界面
    @RequestMapping(value = "recruitScheduleFrame", produces = "text/plain;charset=utf-8")
    public String recruitScheduleFrame(){
        return "recruit-schedule";
    }

    //求职进度表===============数据获取
    @RequestMapping(value = "recruitSchedule", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String recruitSchedule(Integer jobstation,Integer id){
        Jobcontain jobcontain = adminService.recruitSchedule(jobstation);
        return new Gson().toJson(jobcontain);
    }

    //公司简介界面
    @RequestMapping(value = "companyUpdateFrame", produces = "text/plain;charset=utf-8")
    public String companyUpdateFrame(HttpServletRequest request, Model model,Integer id){
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        if(admin == null){
            admin.setCompany(1);
        }
        Company company = adminService.getCompanyById(admin.getCompany());
        model.addAttribute("company",company);
        return "company-update";
    }

    //公司简介修改
    @RequestMapping(value = "companyUpdate", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String companyUpdate(Company company){
        if(company.getId() == null){
            company.setId(1);
        }
        System.out.println(company.getIntroduce());
        Integer res = adminService.companyUpadate(company);

        return ""+res;
    }


}
