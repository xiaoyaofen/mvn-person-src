package com.facedemo.mapper;

import com.facedemo.dto.FaceUserInfo;
import com.facedemo.pojo.UserFaceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jerry
 */
@Mapper
public interface UserFaceInfoMapper {

    /**
     *  获取用户全部信息
     * @return
     */
    List <UserFaceInfo> findUserFaceInfoList();


    /**
     *  新增用户信息
     * @param userFaceInfo
     * @return
     */
    int insertUserFaceInfo(UserFaceInfo userFaceInfo);

    /**
     *  根据分组id获取用户信息
     * @param groupId
     * @return
     */
    List<FaceUserInfo> getUserFaceInfoByGroupId(Integer groupId);




}




