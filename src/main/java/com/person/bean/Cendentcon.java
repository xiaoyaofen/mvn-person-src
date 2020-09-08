package com.person.bean;

public class Cendentcon {
    private Integer id;
    private Integer credentid;
    private Integer userid;

    public Cendentcon() {
    }

    public Cendentcon(Integer id, Integer credentid, Integer userid) {
        this.id = id;
        this.credentid = credentid;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCredentid() {
        return credentid;
    }

    public void setCredentid(Integer credentid) {
        this.credentid = credentid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
