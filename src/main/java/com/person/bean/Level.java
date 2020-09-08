package com.person.bean;

public class Level {
    private Integer id;
    private Integer userid;
    private String time;
    private Integer schoolid;
    private Integer majorid;

    public Level() {
    }

    public Level(Integer id, Integer userid, String time, Integer schoolid, Integer majorid) {
        this.id = id;
        this.userid = userid;
        this.time = time;
        this.schoolid = schoolid;
        this.majorid = majorid;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public Integer getMajorid() {
        return majorid;
    }

    public void setMajorid(Integer majorid) {
        this.majorid = majorid;
    }
}
