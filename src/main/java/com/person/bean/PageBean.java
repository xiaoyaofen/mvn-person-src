package com.person.bean;

import lombok.Data;

import java.util.List;

@Data
public class PageBean<T> {

    private String msg;
    private Integer code;
    private Integer count;
    private List<T> list;

}
