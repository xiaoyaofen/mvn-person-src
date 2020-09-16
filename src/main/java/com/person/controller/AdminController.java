package com.person.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.person.bean.*;
import com.person.service.AdminService;
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
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
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
        System.out.println("======================================"+company.getIntroduce());
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
    public String hiringScheduleFrame(Integer id,Integer jobstation,String title,Model model){
        System.out.println(id); // 学生id
        System.out.println(jobstation);
        Jobcontain jobcontain = adminService.hiringScheduleFrame(jobstation);
        model.addAttribute("job",jobcontain);
        model.addAttribute("userid",id);
        model.addAttribute("jobstation",jobstation);
        model.addAttribute("title",title);
        return "hiring-schedule";
    }

    //求职进度审核=====审核
    @RequestMapping("checkResume")
    @ResponseBody
    public String checkResume(String id,String value,Integer i){
        System.out.println(id);
        System.out.println(value);
        Integer res = adminService.checkResume(id,value,i);
        return res+"";
    }


        // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
        // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
        //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
        public static String myEmailAccount = "1044017081@qq.com"; //修改成自己的QQ号
        public static String myEmailPassword = "qutitixjfdhcbfid";   //授权码

        // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
        // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
        public static String myEmailSMTPHost = "smtp.qq.com";

        // 收件人邮箱（替换为自己知道的有效邮箱）
        public static String receiveMailAccount = "1433274394@qq.com";
//        public static String receiveMailAccount = "948844680@qq.com";

        @RequestMapping("test")
        @ResponseBody
        public  String main() throws Exception {
            // 1. 创建参数配置, 用于连接邮件服务器的参数配置
            Properties props = new Properties();                    // 参数配置
            props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
            props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
            props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

            // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
            //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
            //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。

            // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
            //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
            //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
            final String smtpPort = "465";
            props.setProperty("mail.smtp.port", smtpPort);
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", smtpPort);


            // 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(myEmailAccount, myEmailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
            return "发送成功";
        }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "人才网", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("打折钜惠");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setText("XX用户你好, 今天全场5折, 快来抢购, 错过今天再等一年。。。");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }


    //手机验证码发送
    @RequestMapping(value = "/sendSms")
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
                session.setAttribute("currentLong", newTime);
                //boolean sendFlag = adminService.sendSms("15294560890",code);
            }

        }
        System.out.println(code);
        System.out.println("当前时间"+currentLong);
        return code;

    }

    //数据导出
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response,HttpServletRequest request)  throws IOException {
        Admin admin = (Admin)request.getSession().getAttribute("admin");
        Integer adminid = 1;
        if(admin != null){
            adminid = admin.getId();
        }
        List<BusClick> resultList = adminService.exportExcel(adminid);

//        List<BusClick> resultList = new ArrayList<BusClick>();
//        BusClick busClick = new BusClick();
//        busClick.setCityCode("a1");
//        busClick.setClientVer("a2");
//        busClick.setDate("a3");
//        busClick.setMarkId("a4");
//        busClick.setToaluv("a5");
//        resultList.add(busClick);
//
//        busClick = new BusClick();
//        busClick.setCityCode("b1");
//        busClick.setClientVer("b2");
//        busClick.setDate("b3");
//        busClick.setMarkId("b4");
//        busClick.setToaluv("b5");
//        resultList.add(busClick);

        long t1 = System.currentTimeMillis();
        ExcelUtils.writeExcel(response, resultList, BusClick.class);
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
    }


}
