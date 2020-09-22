package com.person.logSevrvice.impl;

import com.person.bean.Daily;
import com.person.logSevrvice.SystemLogService;
import com.person.mapper.SystemLogMapper;
import com.person.mapper.SystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;


    @Override
    public void insertLog(Daily daily) {
        systemLogMapper.insertSystemLog(daily);
    }
}
