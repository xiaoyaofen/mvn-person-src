package com.person.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.person.bean.*;
import com.person.service.KnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @GetMapping(value = "/teacherSel")
    @ResponseBody
    public Object teacherSel(){
        List<Params> list=knowService.teacherSel();
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
                String maxValue = knowService.findMaxValue("领域");
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

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object upload(HttpServletRequest request, HttpServletResponse response,
                         MultipartFile file) {
        String introduce = request.getParameter("introduce1");
        String id=request.getParameter("id");
        String videoTime=request.getParameter("videoTime");
        try {
            //获取文件名
            String originalName = file.getOriginalFilename();
            //扩展名
            String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
            Date date = new Date();
            //使用UUID+后缀名保存文件名，防止中文乱码问题
            String uuid = UUID.randomUUID() + "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format(date);

            File file1 = new File("");
            String filePath = file1.getCanonicalPath()+ File.separator +"src"+ File.separator +"main"+
                    File.separator +"resources"+File.separator+"static"+File.separator+"X-admin"+File.separator+"upload"+File.separator;
            System.out.println(filePath);
            //"/upload/"最后的的斜杠会被tomcat取消掉，需要把/放在projectPath
//            String savePath = request.getSession().getServletContext().getRealPath("/upload");
            //要保存的问题件路径和名称   /upload/2020-09-09/uuid.jpg
            String projectPath = filePath + File.separator + dateStr + File.separator + uuid + "." + prefix;
            System.out.println("projectPath==" + projectPath);
            File files = new File(projectPath);
//            File files = new File(filePath);
            //打印查看上传路径
            if (!files.getParentFile().exists()) {//判断目录是否存在
                System.out.println("files11111=" + files.getPath());
                files.getParentFile().mkdirs();
            }
            file.transferTo(files); // 将接收的文件保存到指定文件中
            System.out.println(projectPath);
            LayuiData layuiData = new LayuiData();
            layuiData.setCode(0);
            layuiData.setMsg("上传成功");
            String url="\\upload" + File.separator + dateStr + File.separator + uuid + "." + prefix;
            knowService.setCharpter(id,introduce,videoTime,url);
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/findUrl")
    @ResponseBody
    public Object findUrl(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        String url=knowService.findUrl(id);
        return url;
    }

    @GetMapping(value = "/Product")
    public String product() {
        return "Product";
    }

    @RequestMapping(value = "/getProduct")
    @ResponseBody
    public Object getProduct(HttpServletRequest request, HttpServletResponse response){
        String page = request.getParameter("page");
        Integer limit = Integer.parseInt(request.getParameter("limit"));
        Integer page1 = Integer.parseInt(page);
        page1 = (page1 - 1) * limit;
        String state=request.getParameter("state");
        String scope=request.getParameter("scope");
        LayuiData layuiData=knowService.findProduct(page1,limit,scope,state);
        return layuiData;
    }

    @RequestMapping(value = "/changeState")
    @ResponseBody
    public Object changeState(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        String state=request.getParameter("state");
        String reqs=knowService.changeState(id,state);
        return reqs;
    }

    @RequestMapping(value = "/fixProduct")
    @ResponseBody
    public Object fixProduct(HttpServletRequest request, HttpServletResponse response,
                         MultipartFile file) {
        String product = request.getParameter("product");
        String start=request.getParameter("start");
        String over=request.getParameter("over");
        String scope=request.getParameter("lingyu");
        String teacher=request.getParameter("teacher");
        String detial=request.getParameter("detial");
        String startOne=start.substring(0,10);
        String overOne=over.substring(0,10);
        String id=request.getParameter("id");
        String scope1=knowService.findScope(scope);
        String teacher1=knowService.findTeacherParam(teacher);
        try {
            //获取文件名
            String originalName = file.getOriginalFilename();
            //扩展名
            String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
            Date date = new Date();
            //使用UUID+后缀名保存文件名，防止中文乱码问题
            String uuid = UUID.randomUUID() + "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format(date);

            File file1 = new File("");
            String filePath = file1.getCanonicalPath()+ File.separator +"src"+ File.separator +"main"+
                    File.separator +"resources"+File.separator+"static"+File.separator+"X-admin"+File.separator+"images";
            System.out.println(filePath);
            //"/upload/"最后的的斜杠会被tomcat取消掉，需要把/放在projectPath
//            String savePath = request.getSession().getServletContext().getRealPath("/upload");
            //要保存的问题件路径和名称   /upload/2020-09-09/uuid.jpg
            String projectPath = filePath + File.separator + dateStr + File.separator + uuid + "." + prefix;
            System.out.println("projectPath==" + projectPath);
            File files = new File(projectPath);
//            File files = new File(filePath);
            //打印查看上传路径
            if (!files.getParentFile().exists()) {//判断目录是否存在
                System.out.println("files11111=" + files.getPath());
                files.getParentFile().mkdirs();
            }
            file.transferTo(files); // 将接收的文件保存到指定文件中
            System.out.println(projectPath);
            LayuiData layuiData = new LayuiData();
            layuiData.setCode(0);
            layuiData.setMsg("修改成功");
//            String url="\\X-admin" + File.separator +"images"+File.separator+ dateStr + File.separator + uuid + "." + prefix;
            String url="/X-admin" + "/" +"images"+"/"+ dateStr + "/" + uuid + "." + prefix;
            knowService.setProduct(id,product,scope1,teacher1,detial,startOne,url,overOne);
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/ProductMenu/{id}")
    public Object ProductMenu(@PathVariable(value = "id")String id,ModelMap map,HttpServletRequest request, HttpServletResponse response){
        Map<String, List<Menu>> hashmap=knowService.findknowMenu();
        if (hashmap.size()!=0) {
            map.addAttribute("menu", hashmap);
            request.getSession().setAttribute("productID", id);
        }
       return "ProductMenu";
    }

    @RequestMapping(value = "/ProductRoot/{id}")
    public Object Charpterlist(@PathVariable(value = "id")String id,ModelMap map){
        map.put("id",id);
        return "ProductRoot";
    }

    @RequestMapping(value = "/getTwoCharpter/{id}")
    @ResponseBody
    public Object getTwoCharpter(@PathVariable(value = "id")String id,HttpServletRequest request, HttpServletResponse response){
        String productID= (String) request.getSession().getAttribute("productID");
        TreeNode treeNode=knowService.findAllMenu(id,productID);
//        String str="{\"id\":0,\"title\":\"入门简介\",\"children\":[{\"id\":3,\"title\":\"入门\",\"children\":[{\"id\":1,\"title\":\"入门-1\",\"field\": \"\"},{\"id\":2,\"title\":\"入门-2\",\"children\":[],\"spread\":false,\"checked\":false}],\"spread\":true,\"checked\":false},{\"id\":4,\"title\":\"相关资料\",\"children\":[{\"id\":10,\"title\":\"资料介绍-1\",\"children\":[],\"spread\":false,\"checked\":true},{\"id\":11,\"title\":\"资料介绍-2\",\"children\":[],\"spread\":false,\"checked\":false}],\"spread\":true,\"checked\":false}],\"spread\":true,\"checked\":false}";
        return new Gson().toJson(treeNode);
//        return str;
    }

    @RequestMapping(value = "/addTree")
    @ResponseBody
    public Object addTree(HttpServletRequest request, HttpServletResponse response){
        String permList = request.getParameter("permList");
        String productID= (String) request.getSession().getAttribute("productID");
        Gson gson = new Gson();
        List<String> list = gson.fromJson(permList, new TypeToken<ArrayList<String>>() {
        }.getType());
        for (int i=0;i<list.size();i++){
            if (!list.get(i).equals("0")&&!list.get(i).equals("-1")){
                knowService.delAllMenu(list.get(i));
            }
        }
            for (int i=0;i<list.size();i++){
                if (!list.get(i).equals("0")&&!list.get(i).equals("-1")){
                    knowService.addMenu(productID,list.get(i));
                }
            }
            return "章节添加成功";

//        return "章节添加失败，请重试";
    }

    @RequestMapping(value = "/addProduct")
    @ResponseBody
    public Object addProduct(HttpServletRequest request, HttpServletResponse response,
                             MultipartFile file) {
        String product = request.getParameter("product");
        String start=request.getParameter("start");
        String over=request.getParameter("over");
        String scope=request.getParameter("lingyu");
        String teacher=request.getParameter("teacher");
        String detial=request.getParameter("detial");
        String startOne=start.substring(0,10);
        String overOne=over.substring(0,10);
        String id=request.getParameter("id");
        String teacherName=knowService.findTeacherParam(teacher);
        if (teacherName==null){
            String max=knowService.findMaxValue("讲师");
            Integer maxValue=Integer.parseInt(max)+1;
            knowService.addTeacher(teacher,String.valueOf(maxValue));
        }
        String teacher1=knowService.findTeacherParam(teacher);
        String scope1=knowService.findScope(scope);
//        String teacher1=knowService.findTeacherParam(teacher);
        try {
            //获取文件名
            String originalName = file.getOriginalFilename();
            //扩展名
            String prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
            Date date = new Date();
            //使用UUID+后缀名保存文件名，防止中文乱码问题
            String uuid = UUID.randomUUID() + "";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = simpleDateFormat.format(date);

            File file1 = new File("");
            String filePath = file1.getCanonicalPath()+ File.separator +"src"+ File.separator +"main"+
                    File.separator +"resources"+File.separator+"static"+File.separator+"X-admin"+File.separator+"images";
            System.out.println(filePath);
            //"/upload/"最后的的斜杠会被tomcat取消掉，需要把/放在projectPath
//            String savePath = request.getSession().getServletContext().getRealPath("/upload");
            //要保存的问题件路径和名称   /upload/2020-09-09/uuid.jpg
            String projectPath = filePath + File.separator + dateStr + File.separator + uuid + "." + prefix;
            System.out.println("projectPath==" + projectPath);
            File files = new File(projectPath);
//            File files = new File(filePath);
            //打印查看上传路径
            if (!files.getParentFile().exists()) {//判断目录是否存在
                System.out.println("files11111=" + files.getPath());
                files.getParentFile().mkdirs();
            }
            file.transferTo(files); // 将接收的文件保存到指定文件中
            System.out.println(projectPath);
            LayuiData layuiData = new LayuiData();
            layuiData.setCode(0);
            layuiData.setMsg("修改成功");
//            String url="\\X-admin" + File.separator +"images"+File.separator+ dateStr + File.separator + uuid + "." + prefix;
            String url="/X-admin" + "/" +"images"+"/"+ dateStr + "/" + uuid + "." + prefix;
            knowService.addProduct(product,scope1,teacher1,detial,startOne,url,overOne,"1");
            return layuiData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
