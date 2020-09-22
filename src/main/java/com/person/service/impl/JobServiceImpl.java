package com.person.service.impl;

import com.person.aoplog.Log;
import com.person.bean.Menu;
import com.person.bean.Params;
import com.person.bean.Station;
import com.person.mapper.JobMapper;
import com.person.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobMapper jobMapper;

    @Override
    @Log(operationType = "工作菜单", operationName = "admin")
    public Map<String, List<Params>> findJobMenu() {
        Map<String, List<Params>> map = new HashMap<>();
        List<Params> list = jobMapper.findJobMenu();

        for (int i = 0; i < list.size(); i++) {
            Params params=list.get(i);
            List<Params> list1 = jobMapper.findJobMenuTwo(params.getId());
            map.put(params.getName(), list1);
        }
        return map;
    }

    @Override
    @Log(operationType = "查询职业", operationName = "admin")
    public List<Station> findStation(String value) {
        List<Station> list=jobMapper.findStation(value);
        return list;
    }

    @Override
    @Log(operationType = "找具体工作", operationName = "admin")
    public Station findOneStation(String id) {
        Station station=jobMapper.findOneStation(id);
        return station;
    }

}
