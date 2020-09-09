package com.person.service;

import com.person.bean.Menu;
import com.person.bean.Params;

import java.util.List;

public interface KnowService {
    public List<Menu> getKnowMsg(Integer page,Integer limit,String title,String scope);

    public Integer findCount();

    public List<Params> addselect();

    public Integer delKnow(String id);

    public Integer addKnow(String pid,String menuname,String scope);

    public Integer findScope(String scope);
}
