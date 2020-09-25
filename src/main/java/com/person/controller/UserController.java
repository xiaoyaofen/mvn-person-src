package com.person.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import com.PersonProjectApplication;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;
import com.facedemo.base.Result;
import com.facedemo.base.Results;
import com.facedemo.enums.ErrorCodeEnum;
import com.facedemo.pojo.UserFaceInfo;
import com.facedemo.service.FaceEngineService;
import com.facedemo.service.UserFaceInfoService;
import com.google.gson.Gson;
import com.person.bean.*;
import com.person.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.java2d.pipe.SpanIterator;
import sun.reflect.generics.tree.VoidDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //用户登录
    @RequestMapping("/getUser")
    @ResponseBody
    public String getUser(HttpServletRequest request, HttpServletResponse response) {
        String num = request.getParameter("num");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        String code = request.getParameter("code");
        if (num.toUpperCase().equals(code.toUpperCase())) {
            User user = userService.getUser(account, pwd);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                return "登录成功";
            } else {
                return "账号或密码错误";
            }
        } else {
            return "验证码错误";
        }
    }

    //用户注册
    @RequestMapping("/register")
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        System.out.println(account);
        System.out.println(password);
        System.out.println(sex);
        System.out.println(address);
        String num = userService.checkSex(sex);
        System.out.println(num);
        String user = userService.checkAccount(account);
        if (user != null) {
            return "该账号已经存在！";
        } else {
            userService.addUser(account, password, name, num, age, phone, address);
            return "注册成功";

        }
    }

    //用户求职反馈表
    @RequestMapping("/getFeedback")
    @ResponseBody
    public String getFeedback(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer userid = user.getId();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String industry = request.getParameter("industry");
        System.out.println(industry);
        String post = request.getParameter("post");
        System.out.println(post);
        HashMap<String, Object> condition = new HashMap<>();
        Integer startL = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
        Integer endL = Integer.valueOf(page) * Integer.valueOf(limit);
        condition.put("startL", startL);
        condition.put("endL", endL);
        if (startTime != null && !"".equals(startTime)) {
            condition.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            condition.put("endTime", endTime);
        }
        if (industry != null && !"".equals(industry)) {
            condition.put("industry", industry);
        }
        if (post != null && !"".equals(post)) {
            condition.put("post", post);
        }
        List<Feedback> list = userService.getFeedback(String.valueOf(userid), condition);
        Integer count = userService.findCount(String.valueOf(userid), condition);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return new Gson().toJson(layuiData);


    }


    //用户收藏表
    @RequestMapping("/getCollect")
    @ResponseBody
    public String getCollect(HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userid= user.getId();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String industry = request.getParameter("industry");
        String post = request.getParameter("post");
        HashMap<String, Object> condition = new HashMap<>();
        Integer startL = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
        Integer endL = Integer.valueOf(page) * Integer.valueOf(limit);
        condition.put("startL", startL);
        condition.put("endL", endL);
        if (startTime != null && !"".equals(startTime)) {
            condition.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            condition.put("endTime", endTime);
        }
        if (industry != null && !"".equals(industry)) {
            condition.put("industry", industry);
        }
        if (post != null && !"".equals(post)) {
            condition.put("post", post);
        }
        List<Collect> list = userService.getCollect(String.valueOf(userid), condition);
        Integer count = userService.findCount2(String.valueOf(userid), condition);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return new Gson().toJson(layuiData);

    }



    //我的人脉
    @RequestMapping("/getContacts")
    @ResponseBody
    public String getContacts(HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userid= user.getId();
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        HashMap<String, Object> condition = new HashMap<>();
        Integer startL = (Integer.valueOf(page) - 1) * Integer.valueOf(limit);
        Integer endL = Integer.valueOf(page) * Integer.valueOf(limit);
        condition.put("startL", startL);
        condition.put("endL", endL);
        if (startTime != null && !"".equals(startTime)) {
            condition.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            condition.put("endTime", endTime);
        }
        if (number != null && !"".equals(number)) {
            condition.put("number", number);
        }
        if (name != null && !"".equals(name)) {
            condition.put("name", name);
        }
        List<Contacts> list = userService.getContacts(String.valueOf(userid), condition);
        Integer count = userService.findCount3(String.valueOf(userid), condition);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return new Gson().toJson(layuiData);

    }


    @RequestMapping("/gethead")
    @ResponseBody
    public Object upload(HttpServletRequest request, HttpServletResponse response,
                         MultipartFile file) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        try {
            //获取文件名,
            String originalName = file.getOriginalFilename();
            //扩展名
            String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
            Date date = new Date();
            //使用UUID+后缀名保存文件名，防止中文乱码问题
            String uuid = UUID.randomUUID() + "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format(date);
            File file1 = new File("");
            String filePath = file1.getCanonicalPath() + File.separator + "src" + File.separator + "main" +
                    File.separator + "resources" + File.separator + "static" + File.separator + "upload" + File.separator;
            //"/upload/"最后的的斜杠会被tomcat取消掉，需要把/放在projectPath
//            String savePath = request.getSession().getServletContext().getRealPath("/upload");
            //要保存的问题件路径和名称   /upload/2020-09-09/uuid.jpg
//            System.out.println(savePath);
            String projectPath = filePath + File.separator + dateStr + File.separator + uuid + "." + prefix;
//            System.out.println("projectPath==" + projectPath);
            File files = new File(projectPath);
            //打印查看上传路径
            if (!files.getParentFile().exists()) {//判断目录是否存在
//                System.out.println("files11111=" + files.getPath());
//                System.out.println("files11111=" + files.getParentFile().getPath());
                files.getParentFile().mkdirs();
            }
            file.transferTo(files); // 将接收的文件保存到指定文件中
//            System.out.println("文件存储");
//            System.out.println(projectPath);
            LayuiData layuiData = new LayuiData();
            layuiData.setCode(0);
            layuiData.setMsg("上传成功");
            String url = "\\upload" + File.separator + dateStr + File.separator + uuid + "." + prefix;
            userService.down(url, String.valueOf(userId));
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getimg")
    @ResponseBody
    public Object getimg(HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        User img = userService.getimg(String.valueOf(userId));
        return new Gson().toJson(img);
    }


    @RequestMapping("/getInfor")
    @ResponseBody
    public Object getInfor(HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        String name = request.getParameter("name");
        String account = request.getParameter("account");
        String tel = request.getParameter("tel");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        Integer sex1 = userService.getsex(sex);
        userService.Infor(account, name, sex1, tel, address, String.valueOf(userId));
        User img = userService.getimg("1");
        return new Gson().toJson(img);
    }

    //起初的信息
    @RequestMapping("/Infor")
    @ResponseBody
    public Object Infor(HttpServletRequest request, HttpServletResponse response) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        User img = userService.getimg(String.valueOf(userId));
        return new Gson().toJson(img);
    }

    //简历
    @RequestMapping("/findInfor")
    public Object showRecruit(HttpServletRequest request, HttpServletResponse response,Model model) {
        User user1= (User) request.getSession().getAttribute("user");
        Integer userId=user1.getId();
        User user = userService.findInfor(String.valueOf(userId));
        model.addAttribute("user", user);
        return "myresume";
    }

    //查看
    @RequestMapping("/find")
    public Object showFind(HttpServletRequest request, HttpServletResponse response,Model model) {
        User user1= (User) request.getSession().getAttribute("user");
        Integer userId=user1.getId();
        User user = userService.findInfor(String.valueOf(userId));
        model.addAttribute("user", user);
        return "Collect";
    }

    @RequestMapping("/introduce")
    @ResponseBody
    public Object introduce(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String pname = request.getParameter("pname");
        mixture obj = userService.introduce(name, pname);
        return new Gson().toJson(obj);
    }

//获取用户名
    @RequestMapping("/getName")
    @ResponseBody
    public Object getName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String pname = request.getParameter("pname");
        mixture obj = userService.introduce(name, pname);
        return new Gson().toJson(obj);
    }



    //人脸注册的判断
    @RequestMapping("/ifFace")
    @ResponseBody
    public Object ifFace(Model model,HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("account");
        User user = userService.findbyname(name);
        if (user==null){
            return new Gson().toJson(2);
        }else {
            userService.getuserid(name);
            return new Gson().toJson(1);
        }

    }


    @RequestMapping("/getlogin")
    @ResponseBody
    public Object getlogin(HttpServletRequest request, HttpServletResponse response) {
        String faceId = request.getParameter("faceId");
        User user=userService.getFace(faceId);
        request.getSession().setAttribute("user", user);
        return new Gson().toJson(1);
    }
//    @RequestMapping("/getlogin1")
//    @ResponseBody
//    public Object getlogin1(HttpServletRequest request, HttpServletResponse response,Model model) {
//        String faceId = request.getParameter("faceId");
//        User user=userService.getFace(faceId);
//        request.getSession().setAttribute("user", user);
//        String account=user.getAccount();
//        model.addAttribute("user", user);
//        return new Gson().toJson(1);
//    }




    @RequestMapping("/myName")
    public String myName(HttpServletRequest request, HttpServletResponse response,Model model) {
        String account = request.getParameter("account");
        User user=userService.findbyname(account);
        model.addAttribute("user", user);
        return "Person2";
    }


    @RequestMapping("/myName1")
    public String myName1(HttpServletRequest request, HttpServletResponse response,Model model) {
//        String account = request.getParameter("account");

        User user=userService.findbyname("111");
        model.addAttribute("user", user);
        return "Person2";
    }


    //简历
    @RequestMapping("/getjianli")
    public Object getjianli(HttpServletRequest request, HttpServletResponse response,Model model) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        Integer education=user.getEducation();
        Integer a=user.getSex();
        Integer b=user.getEducation();
        User user1=userService.findInfor(String.valueOf(userId));
        Params params=userService.getValue(String.valueOf(education));
        Params params1=userService.Sex(String.valueOf(a));
        Params params2=userService.getExperience(String.valueOf(b));
        model.addAttribute("params2", params2);
        model.addAttribute("params1", params1);
        model.addAttribute("params", params);
        model.addAttribute("user1", user1);
        return "jianli1";
    }
//    简历的基本信息
@RequestMapping("/updatebase")
@ResponseBody
public Object updatebase(HttpServletRequest request, HttpServletResponse response,Model model) {
    User user= (User) request.getSession().getAttribute("user");
    Integer userId=user.getId();
    String name = request.getParameter("name");
    String sex1 = request.getParameter("sex");
    String detial1 = request.getParameter("detial");
    String tel = request.getParameter("tel");
    String email = request.getParameter("email");
    String sex2=userService.updatesex(sex1);
   String detial=userService.updatexperience(detial1);
    userService.updateinfor(name,sex2,detial,tel,email,userId);
    User user1=userService.findInfor(String.valueOf(userId));
    request.getSession().setAttribute("user", user1);
    Integer education=user1.getEducation();
    Integer a=user1.getSex();
    Integer b=user1.getEducation();
    Params params=userService.getValue(String.valueOf(education));
    Params params1=userService.Sex(String.valueOf(a));
    Params params2=userService.getExperience(String.valueOf(b));
    model.addAttribute("params2", params2);
    model.addAttribute("params1", params1);
    model.addAttribute("params", params);
    model.addAttribute("user1", user1);
    return new Gson().toJson(1);
}



    @RequestMapping("/experience")
    @ResponseBody
    public Object experience(HttpServletRequest request, HttpServletResponse response,Model model) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        String myexperience = request.getParameter("myexperience");
         userService.jobexperience(myexperience,String.valueOf(userId));
        User user1=userService.findInfor(String.valueOf(userId));
        request.getSession().setAttribute("user", user1);
        Integer education=user1.getEducation();
        Integer a=user1.getSex();
        Integer b=user1.getEducation();
        Params params=userService.getValue(String.valueOf(education));
        Params params1=userService.Sex(String.valueOf(a));
        Params params2=userService.getExperience(String.valueOf(b));
        model.addAttribute("params2", params2);
        model.addAttribute("params1", params1);
        model.addAttribute("params", params);
        model.addAttribute("user1", user1);
        return new Gson().toJson(1);
    }



    @RequestMapping("/project1")
    @ResponseBody
    public Object project1(HttpServletRequest request, HttpServletResponse response,Model model) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        String project1 = request.getParameter("project1");
        userService.project(project1,String.valueOf(userId));
        User user1=userService.findInfor(String.valueOf(userId));
        request.getSession().setAttribute("user", user1);
        Integer education=user1.getEducation();
        Integer a=user1.getSex();
        Integer b=user1.getEducation();
        Params params=userService.getValue(String.valueOf(education));
        Params params1=userService.Sex(String.valueOf(a));
        Params params2=userService.getExperience(String.valueOf(b));
        model.addAttribute("params2", params2);
        model.addAttribute("params1", params1);
        model.addAttribute("params", params);
        model.addAttribute("user1", user1);
        return new Gson().toJson(1);
    }

    @RequestMapping("/education1")
    @ResponseBody
    public Object education1(HttpServletRequest request, HttpServletResponse response,Model model) {
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        String education1 = request.getParameter("education1");
        userService.education(education1,String.valueOf(userId));
        User user1=userService.findInfor(String.valueOf(userId));
        request.getSession().setAttribute("user", user1);
        Integer education=user1.getEducation();
        Integer a=user1.getSex();
        Integer b=user1.getEducation();
        Params params=userService.getValue(String.valueOf(education));
        Params params1=userService.Sex(String.valueOf(a));
        Params params2=userService.getExperience(String.valueOf(b));
        model.addAttribute("params2", params2);
        model.addAttribute("params1", params1);
        model.addAttribute("params", params);
        model.addAttribute("user1", user1);
        return new Gson().toJson(1);
    }


    @RequestMapping("/self1")
    @ResponseBody
    public Object self1(HttpServletRequest request, HttpServletResponse response,Model model) {
        System.out.println(1456);
        User user= (User) request.getSession().getAttribute("user");
        Integer userId=user.getId();
        String self1 = request.getParameter("self1");
        userService.self(self1,String.valueOf(userId));
        User user1=userService.findInfor(String.valueOf(userId));
        request.getSession().setAttribute("user", user1);
        Integer education=user1.getEducation();
        Integer a=user1.getSex();
        Integer b=user1.getEducation();
        Params params=userService.getValue(String.valueOf(education));
        Params params1=userService.Sex(String.valueOf(a));
        Params params2=userService.getExperience(String.valueOf(b));
        model.addAttribute("params2", params2);
        model.addAttribute("params1", params1);
        model.addAttribute("params", params);
        model.addAttribute("user1", user1);
        return new Gson().toJson(1);
    }


}



