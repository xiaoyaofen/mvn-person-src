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
    public Integer findCount() {
        Integer count=knowMapper.findCount();
        return count;
    }

    @Override
    public List<Params> addselect() {
        List<Params> list=knowMapper.addselect();
        return list;
    }
}
