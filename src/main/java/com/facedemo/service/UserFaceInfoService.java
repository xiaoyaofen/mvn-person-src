package com.facedemo.service;

import com.facedemo.pojo.UserFaceInfo;


import java.util.List;

/**
 * @author Jerry
 */
public interface UserFaceInfoService {

    /**
     *  新增
     * @param userFaceInfo
     * @return
     */
    int insertSelective(UserFaceInfo userFaceInfo);

    /**
     *  查询全部信息
     * @return
     */
    List<UserFaceInfo> findUserFaceInfoList();



}
