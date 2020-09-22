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

    public Admin adminLogIn(String account ,String pwd);

    public List<Role> findRole();

    public Admin checkAccount(String account);

    public Boolean adminRegister(String roleId , String account, String name , String password, String phone,String address, String unit,String qualification);

    public List<Params> selectTrade();

    public LayuiData postManager( String trade, Integer page, Integer pageSize);

    public Boolean delJob(Integer id,String state);


    public String addJob(String job,Integer tradeId);

    public String editJob(String job,Integer tradeId,Integer id);


    public LayuiData checkCompanyList(Integer page,Integer pageSize,String companyName,String state);


    public  Boolean checkCompany(String state, Integer id);

    public String resetPassword( String password, String phone);

    public Admin findAdminByPhone(String phone);


}
