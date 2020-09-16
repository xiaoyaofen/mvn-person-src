package com.person.service.impl;

import com.person.bean.*;
import com.person.mapper.AdminMapper;
import com.person.service.AdminService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        List<Station> list = adminMapper.userSelect(condition,limit,curPage);
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
        List<Params> list = adminMapper.adminBioCheck(limit,curPage,condition,adminId);
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


}
