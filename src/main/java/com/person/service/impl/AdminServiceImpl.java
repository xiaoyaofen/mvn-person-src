package com.person.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.person.bean.*;
import com.person.mapper.AdminMapper;
import com.person.service.AdminService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    public LayuiData<User> getUserByAdmin(HashMap<String, Object> condition, Integer limit, Integer page) {
        LayuiData<User> pageBean = new LayuiData<>();
        Integer conut = adminMapper.getUserByAdminOfNum(condition);
        Integer curPage = limit * (page - 1);
        List<User> list = adminMapper.getUserByAdmin(condition, limit, curPage);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }

    //批量导入学生信息===============================================
    @Override
    public Integer uploadExcel(List<User> userInfoList) {
        return adminMapper.uploadExcel(userInfoList);
    }


    //高校人才推荐数据获取
    @Override
    public LayuiData<Station> userRecommend(HashMap<String, Object> condition, Integer limit, Integer page) {
        LayuiData<Station> pageBean = new LayuiData<Station>();
        Integer conut = adminMapper.userRecommendNum(condition);
        Integer curPage = limit * (page - 1);
        List<Station> list = adminMapper.userRecommend(condition,limit,curPage);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }


    //高校人才推荐 ==============选择人才数据显示
    @Override
    public LayuiData<User> userSelect(HashMap<String, Object> condition, Integer limit, Integer page) {
        LayuiData<User> pageBean = new LayuiData<>();
        Integer conut = adminMapper.userSelectNum(condition);
        Integer curPage = limit * (page - 1);
        List<User> list = adminMapper.userSelect(condition,limit,curPage);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }

    //高校人才推荐 ==============确定选择推荐人选
    @Override
    public Integer userSelectSure(List<Integer> list, Integer jobid) {
        Integer res = adminMapper.userSelectSure(list,jobid);
        return res;
    }

    //公司招聘管理=================招聘修改
    @Override
    public Integer recruitUpdate(Station station, Integer id) {
        return adminMapper.recruitUpdate(station,id);
    }

    //公司招聘管理=================招聘新增
    @Override
    public Integer recruitInsert(Station station, Integer id) {
        return adminMapper.recruitInsert(station,id);
    }

    //公司招聘管理=================管理界面数据获取
    @Override
    public LayuiData<Station> adminRecruit(Integer adminId, Integer limit, Integer page, HashMap<String, Object> condition) {
        LayuiData<Station> pageBean = new LayuiData<>();
        Integer conut = adminMapper.adminRecruitNum(adminId,condition);
        Integer curPage = limit * (page - 1);
        List<Station> list = adminMapper.adminRecruit(adminId,condition,limit,curPage);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }

    //获取下拉菜单的数据
    @Override
    public LayuiData<Params> getOptionData(String type) {
        LayuiData<Params> pageBean = new LayuiData<>();
        List<Params> list = adminMapper.getOptionData(type);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        return pageBean;
    }

    //求职管理====================界面显示
    @Override
    public LayuiData<User> adminBioCheck(Integer limit, Integer page, HashMap<String, Object> condition, Integer adminId) {

        LayuiData<User> pageBean = new LayuiData<>();
        Integer conut = adminMapper.adminBioCheckNum(adminId,condition);
        Integer curPage = limit * (page - 1);
        List<User> list = adminMapper.adminBioCheck(limit,curPage,condition,adminId);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }

    //获取新增注册简历数量
    @Override
    public HashMap<String,Integer> getConutData(){
        HashMap<String ,Integer> condition = new HashMap<>();
        Integer day = adminMapper.getThisDay();
        Integer month = adminMapper.getThisMonth();
        Integer week = adminMapper.getThisWeek();
        condition.put("day",day);
        condition.put("month",month);
        condition.put("week",week);
        return condition;
    }


    //获取求职进度表信息
    @Override
    public Jobcontain recruitSchedule(Integer jobstation) {
        Jobcontain j = adminMapper.recruitSchedule(jobstation);
        return j;
    }


    ////公司简介修改
    @Override
    public Integer companyUpadate(Company company) {
        return adminMapper.companyUpadate(company);
    }


    //获取公司信息
    @Override
    public Company getCompanyById(Integer id) {
        return adminMapper.getCompanyById(id);
    }

    @Override
    public User showRecruit(Integer id) {
        return adminMapper.showRecruit(id);
    }

    //求职进度 ====== 界面展示
    @Override
    public Jobcontain hiringScheduleFrame(Integer jobstation) {
        return adminMapper.hiringScheduleFrame(jobstation);
    }

    //求职进度==========审核
    @Override
    public Integer checkResume(String id, String value,Integer i) {
        return adminMapper.checkResume(id,value,i);
    }

    //求职管理=====删除求职简历
    @Override
    public Integer deleteAminRecruit(Integer id) {
        return adminMapper.deleteAminRecruit(id);
    }




    //aliyuncs的参数

    private String accessKeyID = "LTAI4GATztwWQJWd6NGWvEBJ";

    private String accessKeySecret = "v7iRG7K07X7b6lq3BOIB4w1bUzmsA7";
    //短信api的请求地址  固定

    private String domain  = "dysmsapi.aliyuncs.com";
    //指定地域名称 短信API的就是 cn-hangzhou 不能改变

    private String regionId = "cn-hangzhou";
    //您的申请签名

    private String signName = "人才生态圈";
    //你的模板

    private String templateCode = "SMS_201721401";

        /**
         * 发送短信接口
         *
         * @param phoneNum 手机号
         * @param message     消息
         * @return
         */
        @Override
        public boolean sendSms(String phoneNum, String message) {

            // 指定地域名称 短信API的就是 cn-hangzhou 不能改变  后边填写您的  accessKey 和 accessKey Secret
            DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
            IAcsClient client = new DefaultAcsClient(profile);

            // 创建通用的请求对象
            CommonRequest request = new CommonRequest();
            // 指定请求方式
            request.setSysMethod(MethodType.POST);
            // 短信api的请求地址  固定
            request.setSysDomain(domain);
            //签名算法版本  固定
            request.setSysVersion("2017-05-25");
            //请求 API 的名称
            request.setSysAction("SendSms");
            //指定地域名称
            request.putQueryParameter("RegionId", regionId);
            // 要给哪个手机号发送短信  指定手机号
            request.putQueryParameter("PhoneNumbers", phoneNum);
            // 您的申请签名
            request.putQueryParameter("SignName", signName);
            // 您申请的模板 code
            request.putQueryParameter("TemplateCode", templateCode);

            Map<String, Object> params = new HashMap<>();
            //这里的key就是短信模板中的 ${xxxx}
            params.put("code", message);

            // 放入参数  需要把 map转换为json格式  使用fastJson进行转换
            request.putQueryParameter("TemplateParam", JSON.toJSONString(params));

            try {
                CommonResponse response = client.getCommonResponse(request);
                return response.getHttpResponse().isSuccess();

            }  catch (ClientException e) {
                e.printStackTrace();
            }

            return false;
        }

    /**
     * 文件导出Excel
     *
     * @param adminid 用户序号
     * @return
     */
    @Override
    public List<BusClick> exportExcel(Integer adminid) {
        return adminMapper.exportExcel(adminid);
    }

}
