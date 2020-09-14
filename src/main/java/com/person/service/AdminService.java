package com.person.service;

import com.person.bean.LayuiData;
import com.person.bean.Station;
import com.person.bean.User;
import com.person.bean.TreeNode;
import com.person.bean.UserInfo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public interface AdminService {
    //高校获取用户信息
    LayuiData<UserInfo> getUserByAdmin(HashMap<String, Object> condition, Integer limit, Integer page);

//    List<List<Object>> getBankListByExcel(InputStream inputStream, String originalFilename) throws Exception;

    //批量导入学生信息====================================================
    Integer uploadExcel(List<User> userInfoList);

    //高校人才推荐数据获取================================================
    LayuiData<Station> userRecommend(HashMap<String, Object> condition, Integer limit, Integer page);

    //高校人才推荐 ==============选择人才数据显示
    LayuiData<User> userSelect(HashMap<String, Object> condition, Integer limit, Integer page);

    //高校人才推荐 ==============确定选择推荐人选
    Integer userSelectSure(List<Integer> list, Integer jobid);

}
