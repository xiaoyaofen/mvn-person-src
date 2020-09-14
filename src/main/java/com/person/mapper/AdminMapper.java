package com.person.mapper;

import com.person.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Mapper
@Component
public interface AdminMapper {

    List<User> getUserByAdmin(@Param("condition") HashMap<String, Object> condition, @Param("limit") Integer limit, @Param("page") Integer page);

    Integer getUserByAdminOfNum(@Param("condition") HashMap<String, Object> condition);

    //批量导入学生信息========================================================
    public Integer uploadExcel(@Param("list") List<User> userInfoList);

    //高校人才推荐数据获取========获取总数========================================
    Integer userRecommendNum(@Param("condition") HashMap<String, Object> condition);
    //高校人才推荐数据获取=========获取信息=======================================
    List<Station> userRecommend(@Param("condition")HashMap<String, Object> condition, @Param("limit")Integer limit,@Param("page") Integer page);

    //高校人才推荐 ==============选择人才数据显示 ====== 总数
    Integer userSelectNum(@Param("condition")HashMap<String, Object> condition);
    //高校人才推荐 ==============选择人才数据显示 ======= 数据
    List<Station> userSelect(@Param("condition")HashMap<String, Object> condition,@Param("limit") Integer limit,@Param("page") Integer page);

    //高校人才推荐 ==============确定选择推荐人选 ====== 新增jobcontaion
    Integer userSelectSure(@Param("list") List<Integer> list, @Param("jobid") Integer jobid);

    //公司招聘管理=================招聘新增
    Integer recruitInsert(@Param("station")Station station, @Param("id")Integer id);

    //公司招聘管理=================招聘修改
    Integer recruitUpdate(@Param("station")Station station,@Param("id") Integer id);

    //公司招聘管理=================管理界面数据获取=====总数量
    Integer adminRecruitNum(@Param("id") Integer adminId,@Param("condition")HashMap<String, Object> condition);

    //公司招聘管理=================管理界面数据获取=====
    List<Station> adminRecruit(@Param("id")Integer adminId,@Param("condition")HashMap<String, Object> condition, @Param("limit")Integer limit,@Param("page") Integer curPage);

    // 工具======================获取下拉菜单的数据
    List<Params> getOptionData(@Param("type") String type);

    //求职管理====================界面显示=======总数
    Integer adminBioCheckNum(@Param("id")Integer adminId, @Param("condition")HashMap<String, Object> condition);
    //求职管理====================界面显示=======数据
    List<Params> adminBioCheck(@Param("limit")Integer limit,@Param("page") Integer page,@Param("condition") HashMap<String, Object> condition,@Param("id") Integer adminId);

    //获取本日新增简历总数
    Integer getThisDay();
    //获取本周新增简历总数
    Integer getThisWeek();
    //获取本月新增简历总数
    Integer getThisMonth();

    //获取招聘进度信息
    Jobcontain recruitSchedule(@Param("id") Integer jobstation);

    //公司简介 修改
    Integer companyUpadate(@Param("company") Company company);

    //获取公司信息学
    Company getCompanyById(@Param("id") Integer id);
}
