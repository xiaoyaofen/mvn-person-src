package com.person.service.impl;

import com.person.bean.*;
import com.person.mapper.SystemMapper;
import com.person.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemMapper systemMapper;


    @Override
    public LayuiData getParamList(Integer page, Integer pageSize, String name, String type) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<Params> list = systemMapper.getParamList(start, pageSize, name, type);
        int count = systemMapper.getParamListCount(name, type);
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
        List<String> list = systemMapper.paramsTypeList();
        return list;
    }

    @Override
    public Boolean addParams(String name, String type, String value) {
        Boolean flag = false;

        Params params=systemMapper.checkParams(name,type);
        if(params==null){
            int num = systemMapper.addParams(name, type, value);
            if (num > 0) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Boolean delParams(String state, int id) {
        Boolean flag = false;
        int num = systemMapper.delParams(state, id);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Params checkParams(String paramsName, String paramsType) {

        Params params=systemMapper.checkParams(paramsName,paramsType);

        return params;
    }

    @Override
    public Boolean editParams(String name, int id) {
        Boolean flag = false;
        int num = systemMapper.editParams(name, id);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public LayuiData roleList() {

        LayuiData layuiData = new LayuiData();
        List<Role> roleList = systemMapper.roleList();
        int count = systemMapper.roleListCount();
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

    @Override
    public TreeNode findRight(int roleId) {
        TreeNode treeRootNode=new TreeNode();
        List<TreeNode>rootNode=new ArrayList<>();
        treeRootNode.setId(0);
        treeRootNode.setTitle("系统权限");
        treeRootNode.setSpread(true);
        treeRootNode.setChildren(rootNode);
        List<Menu> allPrimaryMenu=systemMapper.permission(0);
        List<Menu> primaryMenuByRoleId=systemMapper.rightByRoleId(0,roleId);
        for (int i=0;i<allPrimaryMenu.size();i++){
            TreeNode treeNode=new TreeNode();
            treeNode.setTitle(allPrimaryMenu.get(i).getMenuname());
            treeNode.setSpread(true);
            treeNode.setId(allPrimaryMenu.get(i).getId());
            List<TreeNode> childrenNode=new ArrayList<>();
            treeNode.setChildren(childrenNode);
            List<Menu> allSecondaryMenu=systemMapper.permission(allPrimaryMenu.get(i).getId());
            for(int j=0;j<allSecondaryMenu.size();j++){
                TreeNode twoTreeNode=new TreeNode();
                twoTreeNode.setTitle(allSecondaryMenu.get(j).getMenuname());
                twoTreeNode.setId(allSecondaryMenu.get(j).getId());
                for(int k=0;k<primaryMenuByRoleId.size();k++){
                    List<Menu> secondaryMenuByRoleId=systemMapper.rightByRoleId(primaryMenuByRoleId.get(k).getId(),roleId);
                    for(int m=0;m<secondaryMenuByRoleId.size();m++){
                        System.out.println(secondaryMenuByRoleId.get(m).getMenuname()+"=="+allSecondaryMenu.get(j).getMenuname());
                        if(secondaryMenuByRoleId.get(m).getMenuname().equals(allSecondaryMenu.get(j).getMenuname())){
                            twoTreeNode.setChecked(true);
                        }
                    }

                }

                childrenNode.add(twoTreeNode);
            }
            rootNode.add(treeNode);
        }
        return treeRootNode;
    }

    @Override
    public Boolean editRight(int roleId, List<String> list) {
        Boolean flag=false;

          int num=systemMapper.delRight(roleId);

          for(int i=1;i<list.size();i++){
              num=systemMapper.updateRight(Integer.parseInt(list.get(i)),roleId);
          }
          if(num>0){
              flag=true;
          }
        return flag;
    }

    @Override
    public LayuiData adminLog(Integer page, Integer pageSize, String startDate, String endDate) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<Daily> list = systemMapper.adminLog(start,pageSize,startDate,endDate);
        int count = systemMapper.adminLogCount(startDate,endDate);
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
    public LayuiData userLog(Integer page, Integer pageSize, String startDate, String endDate) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<Daily> list = systemMapper.userLog(start,pageSize,startDate,endDate);
        int count = systemMapper.userLogCount(startDate,endDate);
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
    public LayuiData userList(Integer page, Integer pageSize, String school, String state) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<User> list = systemMapper.userList(start,pageSize,school,state);
        int count = systemMapper.userLitCount(school,state);
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
    public Boolean userManager(Integer id, String state) {
        Boolean flag = false;
        int num = systemMapper.userManager(id, state);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Admin adminLogIn(String account, String pwd) {

        Admin admin=systemMapper.adminLogIn(account,pwd);
        return  admin;
    }

    @Override
    public List<Role> findRole() {

        List<Role> list=systemMapper.findRole();
        return list;
    }

    @Override
    public Admin checkAccount(String account) {
        Admin admin=systemMapper.checkAccount(account);

        return admin;
    }

    //2企业
    //3高校
    @Override
    public Boolean adminRegister(String roleId, String account, String name, String password, String phone, String address, String unit, String qualification) {
         Boolean flag=false;
        Integer unitId =null;
        if(roleId.equals("2")){
            Company company=new Company();
            company.setName(unit);
            company.setQualification(qualification);
            Company company1=systemMapper.selectCompany(unit);
            if(company1!=null){
                unitId=company1.getId();
            }else{
                Integer num=systemMapper.addCompany(company);
                if(num>0){
                    unitId=company.getId();
                    System.out.println(unitId);
                }
            }
        }else if (roleId.equals("3")){
             School school=new School();
             school.setName(unit);
           School school1=systemMapper.selectSchool(unit);
           if(school1 !=null){
               unitId=school1.getId();
           }else{
               Integer num1=systemMapper.addSchool(school);
               if(num1>0){
                   unitId=school.getId();
                   System.out.println(unitId);
               }
           }
        }
        Integer count=systemMapper.addAdmin(account,password,name,phone,address,unitId,Integer.parseInt(roleId));
        if(count>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public List<Params> selectTrade() {
        List<Params> list=systemMapper.selectTrade();
        return list;
    }

    @Override
    public LayuiData postManager(String trade, Integer page, Integer pageSize) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<JobTrade> list = systemMapper.postManager(trade,start,pageSize);
        int count = systemMapper.postManagerCount(trade);
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
    public Boolean delJob(Integer id, String state) {
        Boolean flag = false;
        int num = systemMapper.delJob(state, id);
        if (num > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public String addJob(String job, Integer tradeId) {
        String str=null;
        Params params=systemMapper.checkJob(job);
        if(params!=null){
            JobTrade jobTrade=systemMapper.checkJobTrade(Integer.parseInt(params.getValue()),tradeId);
            if(jobTrade==null){
               int num1=systemMapper.addJobTrade(Integer.parseInt(params.getValue()),tradeId) ;
                if(num1>0){
                    str="1";
                }else{
                    str="2";
                }
            }else{
                str="3";
            }
        }else{
            String jobIddStr=systemMapper.maxJob();
            Integer jobId=Integer.parseInt(jobIddStr)+1;
            Integer num2=systemMapper.addJob(jobId,job);
            num2=systemMapper.addJobTrade(jobId,tradeId) ;
            if(num2>0){
                str="1";
            }else{
                str="2";
            }
        }
        return str;
    }

    @Override
    public String editJob(String job, Integer tradeId, Integer id) {
        String str=null;
        Params params=systemMapper.checkJob(job);
        System.out.println(params.getValue());
        if(params!=null){
            JobTrade jobTrade=systemMapper.checkJobTrade(Integer.parseInt(params.getValue()),tradeId);
            if(jobTrade==null){
                int num1=systemMapper.editJobTrade(Integer.parseInt(params.getValue()),tradeId,id) ;
                if(num1>0){
                    str="1";
                }else{
                    str="2";
                }
            }else{
                str="3";
            }
        }else{
            String jobIddStr=systemMapper.maxJob();
            Integer jobId=Integer.parseInt(jobIddStr)+1;
            Integer num2=systemMapper.addJob(jobId,job);
            num2=systemMapper.editJobTrade(jobId,tradeId,id);
            if(num2>0){
                str="1";
            }else{
                str="2";
            }
        }
        return str;
    }

    @Override
    public LayuiData checkCompanyList(Integer page, Integer pageSize, String companyName, String state) {
        LayuiData layuiData = new LayuiData();
        Integer start = (page - 1) * pageSize;
        List<CompanyCheck> list = systemMapper.checkCompanyList(start,pageSize,companyName,state);
        int count = systemMapper.checkCompanyListCount(companyName,state);
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
    public Boolean checkCompany(String state, Integer id) {
        Boolean flag=false;
        int num=systemMapper.checkCompany(state,id);
        if(num>0){
            flag=true;
        }
        return flag;
    }


}
