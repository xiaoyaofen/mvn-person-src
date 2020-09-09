package com.person.service;

import com.person.bean.Menu;
import com.person.bean.Params;

import java.util.List;

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

    public void addScopeParams(String scope);

    public String findMaxValue();
}