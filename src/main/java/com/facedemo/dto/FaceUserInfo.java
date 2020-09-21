package com.facedemo.dto;


import com.facedemo.pojo.UserFaceInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jerry
 */
@Getter
@Setter
public class FaceUserInfo extends UserFaceInfo {

    private String faceId;
    private String name;
    private Integer similarValue;
    private byte[] faceFeature;


}

