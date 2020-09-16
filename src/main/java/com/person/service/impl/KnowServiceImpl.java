package com.person.service.impl;

import com.person.bean.*;
import com.person.mapper.KnowMapper;
import com.person.service.KnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KnowServiceImpl implements KnowService {

    @Autowired
    KnowMapper knowMapper;

    @Override
    public List<Menu> getKnowMsg(Integer page, Integer limit,String title,String scope) {
        List<Menu> list=knowMapper.getKnowMsg(page,limit,title,scope);
        return list;
    }

    @Override
    public Integer findCount(String title,String scope) {
        Integer count=knowMapper.findCount(title,scope);
        return count;
    }

    @Override
    public List<Params> addselect() {
        List<Params> list=knowMapper.addselect();
        return list;
    }

    @Override
    public Integer delKnow(String id) {
        Integer num=knowMapper.delKnow(id);
        return num;
    }

    @Override
    public Integer addKnow(String pid, String menuname, String scope) {
        Integer num=knowMapper.addKnow(pid,menuname,scope);
        return num;
    }

    @Override
    public String findScope(String scope) {
        String scopeId=knowMapper.findScope(scope);
        return scopeId;
    }

    @Override
    public Integer fixKnow(String id, String scope, String menuname) {
        Integer num=knowMapper.fixKnow(id,scope,menuname);
        return num;
    }

    @Override
    public Menu findCourse(String scopeId, String menuname) {
        Menu menu=knowMapper.findCourse(scopeId,menuname);
        return menu;
    }

    @Override
    public Params findScopeParmas(String scope) {
        Params params=knowMapper.findScopeParmas(scope);
        return params;
    }

    @Override
    public Integer addScopeParam(String scope, String maxValue) {
        Integer num=knowMapper.addScopeParam(scope,maxValue);
        return num;
    }

    @Override
    public String findMaxValue(String scope) {
        String maxValue=knowMapper.findMaxValue(scope);
        return maxValue;
    }

    @Override
    public Integer addKnowmenu(String menuname, String detial, String value) {
        Integer num=knowMapper.addKnowmenu(menuname,detial,value);
        return num;
    }

    @Override
    public LayuiData findCharpter(Integer page, Integer limit, String title, String scope) {
        List<Character> list=knowMapper.findCharpter(page,limit,title,scope);
        Integer count=knowMapper.findCharpterCount(title,scope);
        LayuiData layuiData = new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return layuiData;
    }

    @Override
    public Integer findCharpterCount(String title, String scope) {
        Integer count=knowMapper.findCharpterCount(title,scope);
        return count;
    }

    @Override
    public String delCharpter(String id) {
        Integer num=knowMapper.delCharpter(id);
        if (num!=0){
            return "删除成功";
        }else {
            return "删除失败，请重试";
        }
    }

    @Override
    public Charpter seeCharpter(String id) {
        Charpter charpter=knowMapper.seeCharpter(id);
        return charpter;
    }

    @Override
    public void setCharpter(String id, String introduce, String classTime, String url) {
        knowMapper.setCharpter(id,introduce,classTime,url);
    }

    @Override
    public String findUrl(String id) {
        String url=knowMapper.findUrl(id);
        return url;
    }

    @Override
    public LayuiData findProduct(Integer page, Integer limit, String scope,String state) {
        List<Product> list=knowMapper.findProduct(page,limit,scope,state);
        Integer count=knowMapper.findProductCount(scope,state);
        LayuiData layuiData = new LayuiData<User>();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return layuiData;
    }

    @Override
    public Integer findProductCount(String scope, String state) {
        Integer count=knowMapper.findProductCount(scope,state);
        return count;
    }

    @Override
    public String changeState(String id, String state) {
        Integer num=knowMapper.changeState(id,state);
        if (num!=0){
            return "状态修改成功";
        }else {
            return "状态修改失败，请重试";
        }
    }

    @Override
    public List<Params> teacherSel() {
        List<Params> list=knowMapper.teacherSel();
        return list;
    }

    @Override
    public void setProduct(String id, String product, String scope, String teacher, String detial, String start,String picture, String over) {
        knowMapper.setProduct(id,product,scope,teacher,detial,start,picture,over);
    }

    @Override
    public String findTeacherParam(String teacher) {
        String teacher1=knowMapper.findTeacherParam(teacher);
        return teacher1;
    }

    @Override
    public Map<String, List<Menu>> findknowMenu() {
        Map<String, List<Menu>> map = new HashMap<>();
        List<Menu> list = knowMapper.findknowMenu(0);

        for (int i = 0; i < list.size(); i++) {
            Menu menu1 = list.get(i);
            List<Menu> list1 = knowMapper.findknowMenu(menu1.getId());
            map.put(menu1.getMenuname(), list1);
        }
        return map;
    }

    @Override
    public LayuiData getTwoCharpter(String id) {
        List<Menu> list= knowMapper.getTwoCharpter(id);
        Integer count=knowMapper.getTwoCharpterCount(id);
        LayuiData layuiData=new LayuiData();
        layuiData.setMsg("");
        layuiData.setCode(0);
        layuiData.setCount(count);
        layuiData.setData(list);
        return layuiData;
    }

    @Override
    public Integer getTwoCharpterCount(String id) {
        Integer count=knowMapper.getTwoCharpterCount(id);
        return count;
    }

    @Override
    public TreeNode findAllMenu(String id,String productID) {
        TreeNode treeRootNode = new TreeNode();
        List<TreeNode> rootchildren = new ArrayList<>();
        treeRootNode.setId(0);
        String menuname=knowMapper.findKnowmenuName(id);
        treeRootNode.setTitle(menuname);
        treeRootNode.setChildren(rootchildren);
        treeRootNode.setSpread(true);
        treeRootNode.setChecked(false);
        List<Menu> list = knowMapper.getTwoCharpter(id);

        for (int i = 0; i < list.size(); i++) {
            TreeNode treeNode = new TreeNode();
            treeNode.setTitle(list.get(i).getMenuname());
            treeNode.setId(-1);
            treeNode.setSpread(true);
            treeNode.setChecked(false);
            List<TreeNode> children = new ArrayList<>();
            treeNode.setChildren(children);
            List<Menu> list1 = knowMapper.findMenunamebyid(String.valueOf(list.get(i).getId()));
            List<Menu> list2 = knowMapper.findOneMenu(productID);
            for (int j = 0; j < list1.size(); j++) {
                TreeNode twoTreeNode = new TreeNode();
                twoTreeNode.setTitle(list1.get(j).getMenuname());
                twoTreeNode.setId(list1.get(j).getId());
                for (int jj = 0; jj < list2.size(); jj++) {
                    System.out.println(list1.get(j).getMenuname()+"=="+list2.get(jj).getMenuname());
                        if (list1.get(j).getMenuname().equals(list2.get(jj).getMenuname())) {
                            twoTreeNode.setChecked(true);
                        }
                }

                children.add(twoTreeNode);
            }
            rootchildren.add(treeNode);
        }
        return treeRootNode;
    }

    @Override
    public List<Menu> findOneMenu(String productID) {
        List<Menu> list=knowMapper.findOneMenu(productID);
        return list;
    }

    @Override
    public Integer delAllMenu(String productID) {
        Integer num=knowMapper.delAllMenu(productID);
        return num;
    }

    @Override
    public void addMenu(String productID, String charpterID) {
        knowMapper.addMenu(productID,charpterID);
    }

    @Override
    public void addTeacher(String teacher,String value) {
        knowMapper.addTeacher(teacher,value);
    }

    @Override
    public void addProduct(String product, String scope, String teacher, String detial, String start, String picture, String over,String publisher) {
         knowMapper.addProduct(product,scope,teacher,detial,start,picture,over,publisher);
    }


}
