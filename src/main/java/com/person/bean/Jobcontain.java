package com.person.bean;

public class Jobcontain {
    private Integer id;
    private Integer userid;
    private Integer jobprocessid;

    public Jobcontain() {
    }

    public Jobcontain(Integer id, Integer userid, Integer jobprocessid) {
        this.id = id;
        this.userid = userid;
        this.jobprocessid = jobprocessid;
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

    public Integer getJobprocessid() {
        return jobprocessid;
    }

    public void setJobprocessid(Integer jobprocessid) {
        this.jobprocessid = jobprocessid;
    }
}
