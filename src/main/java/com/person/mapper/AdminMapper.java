package com.person.mapper;

import com.person.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Mapper
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
    List<User> userSelect(@Param("condition")HashMap<String, Object> condition,@Param("limit") Integer limit,@Param("page") Integer page);

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
    List<User> adminBioCheck(@Param("limit")Integer limit,@Param("page") Integer page,@Param("condition") HashMap<String, Object> condition,@Param("id") Integer adminId);

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

    //简历展示
    User showRecruit(@Param("id") Integer id);

    //招聘进度=====界面展示
    Jobcontain hiringScheduleFrame(@Param("id") Integer jobstation);

    //招聘进度======审核
    Integer checkResume(@Param("type") String id,@Param("value") String value,@Param("id") Integer id2);

    //求职管理=====删除求职简历
    Integer deleteAminRecruit(@Param("id") Integer id);


    /**
     * 文件导出Excel
     *
     * @param adminid 用户序号
     * @return
     */
    List<BusClick> exportExcel(@Param("id") Integer adminid);

    /**
     * 获取招聘信息
     *
     * @param id 用户序号
     * @return
     */
    Station getStationById(@Param("id") Integer id);


    /**
     * 招聘信息展示
     * @Param id 招聘信息id
     * @return
     */
    Station recruitShowFrame(@Param("id") Integer id);

    /**
     * 投递简历
     * @Param id 用户id
     * @Param id1 招聘信息id
     * @return
     */
    Integer enablejob(@Param("id") Integer id,@Param("jobid") Integer id1);

    /**
     * 判断简历是否投递
     * @Param id 用户id
     * @Param id1 招聘信息id
     * @return
     */
    Integer sureEnablejob(@Param("id")Integer id,@Param("jobid") Integer id1);

    /**
     * 邀请用户 - 发送邮件
     *@Param userid 用户id
     * @Param date 邀请时间
     * @Param address 邀请地点
     * @Param tel 联系方式
     * @Param jobstation 求职表中序号
     * */
    Jobcontain inviteUserByCompany(@Param("jobstation") Integer jobstation);

    //chongzhizhongxin
    Integer payMoney(@Param("money") String money,@Param("id") Integer id);


    /**
     *企业端 ==== 人才导出
     *
     * */
    List<User> exportUserInfo(@Param("page") Integer page,@Param("limit") Integer limit,@Param("condition") HashMap<String,Object> condition);

    /**
     *企业端 ==== 人才导出 总数
     *
     * */
    Integer exportUserInfoNum(@Param("condition")HashMap<String, Object> condition);

    /**
     *企业端 ==== 金钱更新
     *
     * */
    Integer updateMoneyByAdmin(@Param("id") Integer id,@Param("money") String money);

    List<BusClick> daochuwenjian(@Param("list") List<User> list);
}
