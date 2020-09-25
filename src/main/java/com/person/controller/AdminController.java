package com.person.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.person.bean.*;
import com.person.config.AlipayConfig;
import com.person.service.AdminService;
import com.person.util.EailSenderUitl;
import com.person.util.ExcelUtils;
import com.person.util.VerifyUtil;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;

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

            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {
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
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        LayuiData<Station> layuiData = adminService.adminRecruit(adminId,limit,page,condition);
        return gson.toJson(layuiData);
    }


    //公司招聘管理=================招聘修改界面
    @RequestMapping(value = "recruitUpdateFrame", produces = "text/plain;charset=utf-8")
    public String recruitUpdateFrame(Model model,Integer id){
        if(id== null){
            id = 13;
        }
        Station station = adminService.getStationById(id);
        model.addAttribute("station",station);
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


    //求职管理=====================删除求职简历
    @RequestMapping(value = "deleteAminRecruit", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String deleteAminRecruit(Integer id){
        Integer res = adminService.deleteAminRecruit(id);
        return res+"";
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
            admin = new Admin();
            admin.setCompany(1);
        }
        System.out.println(admin.getCompany());
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
        Integer res = adminService.companyUpadate(company);
        return ""+res;
    }

    //简历显示
    @RequestMapping(value = "showRecruit", produces = "text/plain;charset=utf-8")
    public String showRecruit(Model model,Integer id){
        if(id == null){
            id=1;
        }
        User user = adminService.showRecruit(id);
        model.addAttribute("user",user);
        return "resume";
    }

    //招聘进度
    @RequestMapping(value = "hiringScheduleFrame", produces = "text/plain;charset=utf-8")
    public String hiringScheduleFrame(String username,Integer id,Integer jobstation,String title,Model model){
        Jobcontain jobcontain = adminService.hiringScheduleFrame(jobstation);
        model.addAttribute("job",jobcontain);
        model.addAttribute("userid",id);
        model.addAttribute("username",username);
        model.addAttribute("jobstation",jobstation);
        model.addAttribute("title",title);
        return "hiring-schedule";
    }

    //求职进度审核=====审核
    @RequestMapping(value="checkResume", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String checkResume(String id,String value,Integer i){
        Integer res = adminService.checkResume(id,value,i);
        return res+"";
    }


    //手机验证码发送
    @RequestMapping(value = "/sendSms", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String sendSms(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long currentLong = (Long) session.getAttribute("currentLong");
        String code = (String)session.getAttribute("verifyCode");
        if (currentLong == null) {
            code = VerifyUtil.createCode();
            currentLong = new Date().getTime();
            session.setAttribute("verifyCode", code);
            session.setAttribute("currentLong", currentLong);
//        boolean sendFlag = adminService.sendSms("15294560890",code);

        }else {
            Long newTime = new Date().getTime();
            if (newTime - currentLong > (10 * 1000)) {
                code = VerifyUtil.createCode();
                session.setAttribute("verifyCode", code);
                session.setAttribute("currentLong", newTime);
                //boolean sendFlag = adminService.sendSms("15294560890",code);
            }

        }
        System.out.println(code);
        System.out.println("当前时间"+currentLong);
        return code;

    }

    //数据导出
    @RequestMapping(value = "exportExcel", produces = "text/plain;charset=utf-8")
    public void exportExcel(HttpServletResponse response,HttpServletRequest request)  throws IOException {
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        Integer adminid = 1;
        if(admin != null){
            adminid = admin.getId();
        }
        List<BusClick> resultList = adminService.exportExcel(adminid);

        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, resultList, BusClick.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
    }


    /**
     * 简历展示
     * @Param id 书生序号
     * @return
     */
    @RequestMapping("showRecruit2")
    public String showRecruit2(Model model,Integer id){
        if(id== null){
            id = 1;
        }
        User user = adminService.showRecruit(id);
        model.addAttribute("user",user);
        return "resume-show";
    }

    /**
     * 招聘信息展示
     * @Param id 招聘信息id
     * @return
     */
    @RequestMapping("recruitShowFrame")
    public String recruitShowFrame(Model model,Integer id){
        if(id == null){
            id = 13;
        }
        Station station = adminService.recruitShowFrame(id);
        model.addAttribute("station",station );
        return "recruit-show";
    }

    /**
     * 地图显示
     * @Param id 招聘信息id
     * @return
     */
    @RequestMapping("recruitSelectMap")
    public String recruitSelectMap(Model model,Integer id){
        if(id == null){
            id = 13;
        }
        Station station = adminService.recruitShowFrame(id);
        model.addAttribute("station",station );
        return "recruit-select-map";
    }


    @RequestMapping(value="enablejob" , produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String enablejob(HttpServletRequest request,Integer id){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            user = new User();
            user.setId(1);
        }
        Integer res = adminService.enablejob(user.getId(),id);
        return res+"";
    }


    /**
     * 充值中心界面显示
     *
     * */
    @RequestMapping("accountRecharge" )
    public String accountRecharge(HttpServletRequest request,Model model){
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        if(admin == null){
            admin = new Admin();
            admin.setMoney("20");
            admin.setName("小明");
        }
        model.addAttribute("admin",admin);
        return "admin-chongzhizhongxin";
    }

    /**
     * 充值中心操作
     *
     * */
    @RequestMapping("pay")
    @ResponseBody
    public String payMoney(HttpServletRequest request,String docVlGender) throws AlipayApiException {
        if(docVlGender == null){
            docVlGender = "20";
        }
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        Integer id = 1;
        String adminMoney = "20";
        if(admin != null){
            id = admin.getId();
            adminMoney = admin.getMoney();
        }

        adminMoney = Double.parseDouble(adminMoney) + Double.parseDouble(docVlGender) +"";
        admin.setMoney(adminMoney);
        request.getSession().setAttribute("admin",admin);
        Integer res = adminService.payMoney(adminMoney,id);
        long l=Date.parse(new Date().toString());
        String WIDTCout_trade_no = l+"";
        String WIDsubject = "充值";
        String WIDtotal_amount = docVlGender;
        String WIDbody = "测试充值";
        AlipayConfig alipayConfig=new AlipayConfig();
        AlipayClient alipayClient=new DefaultAlipayClient(alipayConfig.gatewayUrl,alipayConfig.app_id,alipayConfig.RSA_PRIVATE_KEY,alipayConfig.FORMAT
                ,alipayConfig.charset,alipayConfig.alipay_public_key,alipayConfig.sign_type);

//        创建请求
        AlipayTradeWapPayRequest alipayTradeWapPayRequest=new AlipayTradeWapPayRequest();
//        传入参数
        AlipayTradeWapPayModel alipayTradeWapPayModel=new AlipayTradeWapPayModel();
        alipayTradeWapPayModel.setOutTradeNo(WIDTCout_trade_no);
        alipayTradeWapPayModel.setSubject(WIDsubject);
        alipayTradeWapPayModel.setTotalAmount(WIDtotal_amount);
        alipayTradeWapPayModel.setBody(WIDbody);
        alipayTradeWapPayRequest.setBizModel(alipayTradeWapPayModel);
        alipayTradeWapPayRequest.setNotifyUrl(alipayConfig.notify_url);
        alipayTradeWapPayRequest.setReturnUrl(alipayConfig.return_url);
        String form = alipayClient.pageExecute(alipayTradeWapPayRequest).getBody();
        return form;
    }

    /**
     * 企业端 ====人才导出界面
     *
     * */
    @RequestMapping("exportUserInfoFrame")
    public String exportUserInfoFrame(){
        return "company-chaxunrencai";
    }


    /**
     *企业端 ==== 人才导出
     *
     * */
    @RequestMapping("exportUserInfo")
    @ResponseBody
    public String exportUserInfo(Integer limit,Integer page,Integer sex, Integer education,Integer experience){
        HashMap<String,Object> condition = new HashMap<>();
        if(sex != null){
            condition.put("sex",sex);
        }
        if(education != null){
            condition.put("education",education);
        }
        if(experience != null){
            condition.put("experience",experience);
        }
        LayuiData<User> layuiData = adminService.exportUserInfo(limit,page,condition);
        Gson gson = new Gson();
        return gson.toJson(layuiData);
    }


    //判断数据
    @RequestMapping(value = "exportExcelByCompany", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String exportExcelByCompany(HttpServletResponse response,HttpServletRequest request,String userList)  throws IOException {
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        Integer adminid = 1;
        if(admin != null){
            adminid = admin.getId();
        }

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = builder.create();
        List<User> list = gson.fromJson(userList, new TypeToken<List<User>>() {}.getType());

        String oldMoney = admin.getMoney();
        String newMoney = (list.size() * 0.1) +"";
        Double d = Double.parseDouble(oldMoney) - Double.parseDouble(newMoney);
        if(d>=0){
            admin.setMoney(d+"");
            request.getSession().setAttribute("admin",admin);
            adminService.updateMoneyByAdmin(adminid,d+"");
            List<BusClick> resultList = adminService.daochuwenjian(list);
            request.getSession().setAttribute("resultList",resultList);

            return "2";
        }else{
            return "1";
        }
    }

    //文件导出
    @RequestMapping(value = "daochu", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void daochu(HttpServletResponse response,HttpServletRequest request){
        List<BusClick> resultList = (List<BusClick>)request.getSession().getAttribute("reresultList");
        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, resultList, BusClick.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
    }

    /**
     * 邀请用户弹窗内容
     *
     * */
    @RequestMapping(value="inviteUserByCompanyFrame" , produces = "text/plain;charset=utf-8")
    public String inviteUserByCompanyFrame(){
        return "admin-inviteUserByCompany";
    }


    /**
     * 处理用户简历弹窗内容
     *
     * */
    @RequestMapping(value="handleUserByCompanyFrame" , produces = "text/plain;charset=utf-8")
    public String handleUserByCompanyFrame(){
        return "admin-handleUserByCompanyFrame";
    }


    /**
     * 邀请用户 - 发送邮件
     *
     * */
    @RequestMapping(value="inviteUserByCompany" , produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String inviteUserByCompany(Integer userid,String date,String address,String tel,Integer jobstation) throws Exception {
        Integer res = adminService.inviteUserByCompany(userid,date,address,tel,jobstation);
        return ""+res;
    }



}
