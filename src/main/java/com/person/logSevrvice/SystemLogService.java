package com.person.logSevrvice;

import com.person.bean.Daily;
import org.springframework.stereotype.Service;


public interface SystemLogService {

    public void insertLog(Daily daily);
}
