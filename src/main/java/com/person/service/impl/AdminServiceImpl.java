package com.person.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.person.aoplog.Log;
import com.person.bean.*;
import com.person.mapper.AdminMapper;
import com.person.service.AdminService;
import com.person.util.EailSenderUitl;
import com.person.util.SmsSenderUtil;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    @Log(operationType = "查询学生列表", operationName = "admin")
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
    @Log(operationType = "导入学生信息", operationName = "admin")
    public Integer uploadExcel(List<User> userInfoList) {
        return adminMapper.uploadExcel(userInfoList);
    }


    //高校人才推荐数据获取
    @Override
    @Log(operationType = "获取学生数据", operationName = "admin")
    public LayuiData<Station> userRecommend(HashMap<String, Object> condition, Integer limit, Integer page) {
        LayuiData<Station> pageBean = new LayuiData<Station>();
        Integer conut = adminMapper.userRecommendNum(condition);
        Integer curPage = limit * (page - 1);
        List<Station> list = adminMapper.userRecommend(condition,limit,curPage);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String s = gson.toJson(list);
        System.out.println(gson.toJson(list));
        ArrayList<Station> cars = gson.fromJson(s,new TypeToken<ArrayList<Station>>() {}.getType());
        pageBean.setData(cars);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }


    //高校人才推荐 ==============选择人才数据显示
    @Override
    @Log(operationType = "查询学生列表", operationName = "admin")
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
    @Log(operationType = "确定选择推荐人选", operationName = "admin")
    public Integer userSelectSure(List<Integer> list, Integer jobid) {
        Integer res = adminMapper.userSelectSure(list,jobid);
        return res;
    }

    //公司招聘管理=================招聘修改
    @Override
    @Log(operationType = "招聘修改", operationName = "admin")
    public Integer recruitUpdate(Station station, Integer id) {
        return adminMapper.recruitUpdate(station,id);
    }

    //公司招聘管理=================招聘新增
    @Override
    @Log(operationType = "招聘新增", operationName = "admin")
    public Integer recruitInsert(Station station, Integer id) {
        return adminMapper.recruitInsert(station,id);
    }

    //公司招聘管理=================管理界面数据获取
    @Override
    @Log(operationType = "招聘管理数据获取", operationName = "admin")
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
    @Log(operationType = "下拉菜单", operationName = "admin")
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
    @Log(operationType = "求职管理数据获取", operationName = "admin")
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
    @Log(operationType = "新增注册简历数量", operationName = "admin")
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
    @Log(operationType = "求职进度表信息", operationName = "admin")
    public Jobcontain recruitSchedule(Integer jobstation) {
        Jobcontain j = adminMapper.recruitSchedule(jobstation);
        return j;
    }


    ////公司简介修改
    @Override
    @Log(operationType = "公司简介修改", operationName = "admin")
    public Integer companyUpadate(Company company) {
        return adminMapper.companyUpadate(company);
    }


    //获取公司信息
    @Override
    @Log(operationType = "公司获取", operationName = "admin")
    public Company getCompanyById(Integer id) {
        return adminMapper.getCompanyById(id);
    }

    @Override
    @Log(operationType = "获取公司信息", operationName = "admin")
    public User showRecruit(Integer id) {
        return adminMapper.showRecruit(id);
    }

    //求职进度 ====== 界面展示
    @Override
    @Log(operationType = "求职进度", operationName = "admin")
    public Jobcontain hiringScheduleFrame(Integer jobstation) {
        return adminMapper.hiringScheduleFrame(jobstation);
    }

    //求职进度==========审核
    @Override
    @Log(operationType = "求职进度审核", operationName = "admin")
    public Integer checkResume(String id, String value,Integer i) {
        return adminMapper.checkResume(id,value,i);
    }

    //求职管理=====删除求职简历
    @Override
    @Log(operationType = "删除求职简历", operationName = "admin")
    public Integer deleteAminRecruit(Integer id) {
        return adminMapper.deleteAminRecruit(id);
    }



    /**
     * 文件导出Excel
     *
     * @param adminid 用户序号
     * @return
     */
    @Override
    @Log(operationType = "文件导出", operationName = "admin")
    public List<BusClick> exportExcel(Integer adminid) {
        return adminMapper.exportExcel(adminid);
    }

    /**
     * 获取招聘信息
     *
     * @param id 招聘信息序号
     * @return
     */
    @Override
    @Log(operationType = "招聘信息获取", operationName = "admin")
    public Station getStationById(Integer id) {
        return adminMapper.getStationById(id);
    }

    /**
     * 招聘信息展示
     * @Param id 招聘信息id
     * @return
     */
    @Override
    @Log(operationType = "招聘信息展示", operationName = "admin")
    public Station recruitShowFrame(Integer id) {

        return adminMapper.recruitShowFrame(id);
    }


    /**
     * 投递简历
     * @Param id 用户id
     * @Param id1 招聘信息id
     * @return
     */
    @Override
    @Log(operationType = "投递简历", operationName = "user")
    public Integer enablejob(Integer id, Integer id1) {
        Integer res = adminMapper.sureEnablejob(id,id1);
        if(res < 1){
             res = adminMapper.enablejob(id,id1);
        }
        return res;
    }


    /**
     * 邀请用户 - 发送邮件
     *@Param userid 用户id
     * @Param date 邀请时间
     * @Param address 邀请地点
     * @Param tel 联系方式
     * @Param jobstation 求职表中序号
     * */
    @Override
    @Log(operationType = "邀请用户", operationName = "admin")
    public Integer inviteUserByCompany(Integer userid, String date, String address, String tel, Integer jobstation) throws Exception {
        Jobcontain station = adminMapper.inviteUserByCompany(jobstation);
        EailSenderUitl.sendMail(date,station.getCompany(),station.getUsername(),address,station.getEmail());
        return adminMapper.checkResume("two","已邀请",jobstation);
    }

    @Override
    @Log(operationType = "充值", operationName = "admin")
    public Integer payMoney(String money, Integer id) {
        return adminMapper.payMoney(money,id);
    }


    /**
     *企业端 ==== 人才导出
     *
     * */
    @Override
    @Log(operationType = "查询人才", operationName = "admin")
    public LayuiData<User> exportUserInfo(Integer limit,Integer page,HashMap<String, Object> condition) {
        Integer total = adminMapper.exportUserInfoNum(condition);
        Integer curPage = limit * (page - 1);
        List<User> userList = adminMapper.exportUserInfo(limit,curPage,condition);
        LayuiData<User> layuiData = new LayuiData<>();
        layuiData.setCount(total);
        layuiData.setData(userList);
        layuiData.setMsg("");
        layuiData.setCode(0);
        return layuiData;
    }

    @Override
    @Log(operationType = "金钱交互", operationName = "admin")
    public Integer updateMoneyByAdmin(Integer adminid, String s) {
        return adminMapper.updateMoneyByAdmin(adminid,s);
    }

    @Override
    @Log(operationType = "文件导出", operationName = "admin")
    public List<BusClick> daochuwenjian(List<User> list) {
        List<BusClick> list1 = adminMapper.daochuwenjian(list);

        return list1;
    }


}
