package com.person.bean;

//招聘关联表
public class Jobcontain {
    private Integer id;
    private Integer userid;
    private Integer jobprocessid;
    private Integer stateid;
    private String one;
    private String two;
    private String three;
    private String four;

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

    public Integer getStateid() {
        return stateid;
    }

    public void setStateid(Integer stateid) {
        this.stateid = stateid;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }
}
