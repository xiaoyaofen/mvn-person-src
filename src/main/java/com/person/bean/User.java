package com.person.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description
 * @Author  linmengmeng
 * @Date 2020-09-07 23:35:21
 */

@Data
public class User{


    private Integer id;
    private String account;
    private String pwd;
    private String name;
    private Integer sex;
    private Integer age;
    private String tel;
    private String address;
    private String state;
    private String money;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String jobstate;
}

