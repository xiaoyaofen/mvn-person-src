package com.person.bean;


import java.util.List;

public class PageBean<T> {

    private String msg;
    private Integer code;
    private Integer count;
    private List<T> list;

    public PageBean() {
    }

    public PageBean(String msg, Integer code, Integer count, List<T> list) {
        this.msg = msg;
        this.code = code;
        this.count = count;
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
