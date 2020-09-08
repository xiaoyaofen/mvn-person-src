package com.person.bean;

public class Workexpress {
    private Integer id;
    private String time;
    private String company;
    private String work;
    private Integer userid;

    public Workexpress() {
    }

    public Workexpress(Integer id, String time, String company, String work, Integer userid) {
        this.id = id;
        this.time = time;
        this.company = company;
        this.work = work;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
