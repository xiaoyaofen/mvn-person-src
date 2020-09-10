package com.person.service;

import com.person.bean.LayuiData;
import com.person.bean.User;
import com.person.bean.UserInfo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public interface AdminService {
    //高校获取用户信息
    LayuiData<UserInfo> getUserByAdmin(HashMap<String, Object> condition, Integer limit, Integer page);

//    List<List<Object>> getBankListByExcel(InputStream inputStream, String originalFilename) throws Exception;

    public LayuiData getParamList(Integer page,Integer pageSize,String name,String type);

    public List<String> paramsTypeList();

    public Boolean addParams(String name,String type,String value);

    public Boolean delParams(String state,int id);

    public Boolean editParams(String name, int id );

    public LayuiData roleList();

    //批量导入学生信息====================================================
    Integer uploadExcel(List<User> userInfoList);
}
