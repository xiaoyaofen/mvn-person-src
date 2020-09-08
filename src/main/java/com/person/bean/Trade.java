package com.person.bean;

public class Trade {
    private Integer id;
    private String name;
    private String introduce;

    public Trade() {
    }

    public Trade(Integer id, String name, String introduce) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
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
}
