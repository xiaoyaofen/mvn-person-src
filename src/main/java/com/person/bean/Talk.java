package com.person.bean;

public class Talk {
    private Integer id;
    private Integer userid;
    private String name;
    private String message;
    private String time;

    public Talk() {
    }

    public Talk(Integer id, Integer userid, String name, String message, String time) {
        this.id = id;
        this.userid = userid;
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
