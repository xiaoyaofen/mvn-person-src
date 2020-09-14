package com.person.service;

import com.person.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SystemService {


    public LayuiData getParamList(Integer page, Integer pageSize, String name, String type);

    public List<String> paramsTypeList();

    public Boolean addParams(String name, String type, String value);

    public Boolean delParams(String state, int id);

    public Params checkParams(String paramsName,String paramsType);


    public Boolean editParams(String name, int id);

    public LayuiData roleList();


    public TreeNode findRight(int roleId);

    public Boolean editRight(int roleId, List<String> list);


    public LayuiData adminLog(Integer page,Integer pageSize,String startDate,String endDate);

    public LayuiData userLog(Integer page,Integer pageSize,String startDate,String endDate);

    public LayuiData userList(Integer page,Integer pageSize,String school,String state);

    public Boolean userManager(Integer id,String state);
}
