package com.person.service;

import com.person.bean.*;

import java.util.List;
import java.util.Map;

public interface KnowService {
    public List<Menu> getKnowMsg(Integer page,Integer limit,String title,String scope);

    public Integer findCount(String title,String scope);

    public List<Params> addselect();

    public Integer delKnow(String id);

    public Integer addKnow(String pid,String menuname,String scope);

    public String findScope(String scope);

    public Integer fixKnow(String id,String scope,String menuname);

    public Menu findCourse(String scopeId,String menuname);

    public Params findScopeParmas(String scope);

    public Integer addScopeParam(String scope,String maxValue);

    public String findMaxValue(String scope);

    public Integer addKnowmenu(String menuname,String detial,String value);

    public LayuiData findCharpter(Integer page, Integer limit, String title, String scope);

    public Integer findCharpterCount(String title,String scope);

    public String delCharpter(String id);

    public Charpter seeCharpter(String id);

    public void setCharpter(String id,String introduce,String classTime,String url);

    public String findUrl(String id);

    public LayuiData findProduct(Integer page,Integer limit,String scope,String state);

    public Integer findProductCount(String scope,String state);

    public String changeState(String id,String state);

    public List<Params> teacherSel();

    public void setProduct(String id,String product,String scope,String teacher,String detial,String start,String picture,String over);

    public String findTeacherParam(String teacher);

    public Map<String, List<Menu>> findknowMenu();

    public LayuiData getTwoCharpter(String id);

    public Integer getTwoCharpterCount(String id);

    public TreeNode findAllMenu(String id,String productID);

    public List<Menu> findOneMenu(String productID);

    public Integer delAllMenu(String productID);

    public void addMenu(String productID,String charpterID);

    public void addTeacher(String teacher,String value);

    public void addProduct(String product,String scope,String teacher,String detial,String start,String picture,String over,String publisher);

}