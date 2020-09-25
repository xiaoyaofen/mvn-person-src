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

    //简历查看
    User showRecruit(Integer id);

    //招聘进度=====界面展示
    Jobcontain hiringScheduleFrame(Integer jobstation);

    //招聘进度=====审核
    Integer checkResume(String id, String value,Integer i);

    //求职管理=====删除求职简历
    Integer deleteAminRecruit(Integer id);

    //excel导出
    List<BusClick> exportExcel(Integer adminid);

    //获取发布简历
    Station getStationById(Integer id);

    /**
     * 招聘信息展示
     * @Param id 招聘信息id
     * @return
     */
    Station recruitShowFrame(Integer id);


    /**
     * 投递简历
     * @Param id 用户id
     * @Param id1 招聘信息id
     * @return
     */
    Integer enablejob(Integer id, Integer id1);


    /**
     * 邀请用户 - 发送邮件
     *@Param userid 用户id
     * @Param date 邀请时间
     * @Param address 邀请地点
     * @Param tel 联系方式
     * @Param jobstation 求职表中序号
     * */
    Integer inviteUserByCompany(Integer userid, String date, String address, String tel, Integer jobstation) throws Exception;

    /**
     * 邀请用户 - chongzhizhongxin
     *@Param money
     * */
    Integer payMoney(String money,Integer id);

    /**
     *企业端 ==== 人才导出
     *
     * */
    LayuiData<User> exportUserInfo(Integer limit,Integer page,HashMap<String, Object> condition);

    Integer updateMoneyByAdmin(Integer adminid, String s);

    public List<BusClick> daochuwenjian(List<User> list);
}
