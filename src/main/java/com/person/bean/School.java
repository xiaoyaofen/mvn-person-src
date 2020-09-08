package com.person.bean;

public class School {
    private Integer id;
    private String name;
    private String ip;
    private String introduce;
    private String state;

    public School() {
    }

    public School(Integer id, String name, String ip, String introduce, String state) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.introduce = introduce;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
