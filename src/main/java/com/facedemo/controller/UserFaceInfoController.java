package com.facedemo.controller;


import com.facedemo.pojo.UserFaceInfo;
import com.facedemo.service.UserFaceInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry
 */
@RestController
public class UserFaceInfoController {

    @Resource
    private UserFaceInfoService userFaceInfoService;

    @GetMapping("/userInfo")
    public List<UserFaceInfo> getUserInfo() {
        List<UserFaceInfo> list = new ArrayList<>();
        list = userFaceInfoService.findUserFaceInfoList();
        return list;
    }


}
