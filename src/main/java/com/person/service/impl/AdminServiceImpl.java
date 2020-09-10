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
    public LayuiData<UserInfo> getUserByAdmin(HashMap<String, Object> condition, Integer limit, Integer page) {
        LayuiData<UserInfo> pageBean = new LayuiData<>();
        Integer conut = adminMapper.getUserByAdminOfNum(condition);
        Integer curPage = limit * (page - 1);
        List<UserInfo> list = adminMapper.getUserByAdmin(condition, limit, curPage);
        pageBean.setData(list);
        pageBean.setMsg("");
        pageBean.setCode(0);
        pageBean.setCount(conut);
        return pageBean;
    }



    @Override
    public LayuiData getParamList(Integer page, Integer pageSize, String name, String type) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<Params> list = adminMapper.getParamList(start, pageSize, name, type);
        int count = adminMapper.getParamListCount(name, type);
        if (list.size() > 0) {
            layuiData.setCode(0);
            layuiData.setMsg("");
            layuiData.setCount(count);
            layuiData.setData(list);
        } else {
            layuiData.setCode(1);
            layuiData.setMsg("查询失败");
        }
        return layuiData;
    }

    @Override
    public List<String> paramsTypeList() {
        List<String> list = adminMapper.paramsTypeList();
        return list;
    }

    @Override
    public Boolean addParams(String name, String type, String value) {
        Boolean flag = false;
        int num = adminMapper.addParams(name, type, value);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean delParams(String state, int id) {
        Boolean flag = false;
        int num = adminMapper.delParams(state, id);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean editParams(String name, int id) {
        Boolean flag = false;
        int num = adminMapper.editParams(name, id);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public LayuiData roleList() {

        LayuiData layuiData = new LayuiData();
        List<Role> roleList = adminMapper.roleList();
        int count = adminMapper.roleListCount();
        if (roleList.size() > 0) {
            layuiData.setCode(0);
            layuiData.setMsg("");
            layuiData.setCount(count);
            layuiData.setData(roleList);
        } else {
            layuiData.setCode(1);
            layuiData.setMsg("查询失败");
        }
        return layuiData;
    }

    //批量导入学生信息===============================================
    @Override
    public Integer uploadExcel(List<User> userInfoList) {
        return adminMapper.uploadExcel(userInfoList);
    }


    //高校人才推荐数据获取
    @Override
    public LayuiData<Station> userRecommend(HashMap<String, Object> condition, Integer limit, Integer page) {
        LayuiData<Station> pageBean = new LayuiData<>();
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


}
