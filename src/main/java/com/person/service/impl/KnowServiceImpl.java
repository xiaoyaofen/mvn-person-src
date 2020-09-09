package com.person.service.impl;

import com.person.bean.Menu;
import com.person.bean.Params;
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
    public void addScopeParams(String scope) {

    }

    @Override
    public String findMaxValue() {
        String maxValue=knowMapper.findMaxValue();
        return maxValue;
    }


}
