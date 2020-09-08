package com.person.bean;

public class Job {
    private Integer id;
    private String name;
    private String introduce;
    private Integer trade;

    public Job() {
    }

    public Job(Integer id, String name, String introduce, Integer trade) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.trade = trade;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getTrade() {
        return trade;
    }

    public void setTrade(Integer trade) {
        this.trade = trade;
    }
}
