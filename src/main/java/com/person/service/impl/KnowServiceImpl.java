package com.person.service.impl;

import com.person.bean.*;
import com.person.mapper.KnowMapper;
import com.person.service.KnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public String findMaxValue() {
        String maxValue=knowMapper.findMaxValue();
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
    public Integer setProduct(String id, String product, String scope, String teacher, String detial, String start, String over) {
        return null;
    }


}
