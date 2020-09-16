package com.person.service;

import com.person.bean.*;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public interface AdminService {
    //高校获取用户信息
    LayuiData<User> getUserByAdmin(HashMap<String, Object> condition, Integer limit, Integer page);

    //批量导入学生信息====================================================
    Integer uploadExcel(List<User> userInfoList);

    //高校人才推荐数据获取================================================
    LayuiData<Station> userRecommend(HashMap<String, Object> condition, Integer limit, Integer page);

    //高校人才推荐 ==============选择人才数据显示
    LayuiData<User> userSelect(HashMap<String, Object> condition, Integer limit, Integer page);

    //高校人才推荐 ==============确定选择推荐人选
    Integer userSelectSure(List<Integer> list, Integer jobid);

    //公司招聘管理=================招聘修改
    Integer recruitUpdate(Station station, Integer id);

    //公司招聘管理=================招聘新增
    Integer recruitInsert(Station station, Integer id);

    //公司招聘管理=================管理界面数据获取
    LayuiData<Station> adminRecruit(Integer amdinId, Integer limit, Integer page, HashMap<String, Object> condition);

    //获取下拉菜单的信息
    LayuiData<Params> getOptionData(String type);

    //求职管理====================界面显示
    LayuiData<User> adminBioCheck(Integer limit, Integer page, HashMap<String, Object> condition, Integer adminId);

    //获取新增注册简历数量
    HashMap<String,Integer>  getConutData();

    //获取求职进度表信息
    Jobcontain recruitSchedule(Integer jobstation);

    //公司简介修改
    Integer companyUpadate(Company company);

    //获取公司的信息
    Company getCompanyById(Integer id);
}
