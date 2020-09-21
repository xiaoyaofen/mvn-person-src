package com.facedemo.service.impl;

import com.facedemo.mapper.UserFaceInfoMapper;
import com.facedemo.pojo.UserFaceInfo;

import com.facedemo.service.UserFaceInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jerry
 */
@Service("userFaceInfoService")
public class UserFaceInfoServiceImpl implements UserFaceInfoService {

    @Resource
    private UserFaceInfoMapper userFaceInfoMapper;

    @Override
    public int insertSelective(UserFaceInfo userFaceInfo) {
        int result = userFaceInfoMapper.insertUserFaceInfo(userFaceInfo);
        if (result > 0) {
            return result;
        }
        return 0;
    }

    @Override
    public List<UserFaceInfo> findUserFaceInfoList() {
        return userFaceInfoMapper.findUserFaceInfoList();
    }
}
