package com.person.bean;

public class CV {
    private Integer id;
    private Integer userid;
    private String birthday;
    private String level;
    private String credent;
    private String introduct;
    private String specialty;
    private Integer schoolid;

    public CV() {
    }

    public CV(Integer id, Integer userid, String birthday, String level, String credent, String introduct, String specialty, Integer schoolid) {
        this.id = id;
        this.userid = userid;
        this.birthday = birthday;
        this.level = level;
        this.credent = credent;
        this.introduct = introduct;
        this.specialty = specialty;
        this.schoolid = schoolid;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCredent() {
        return credent;
    }

    public void setCredent(String credent) {
        this.credent = credent;
    }

    public String getIntroduct() {
        return introduct;
    }

    public void setIntroduct(String introduct) {
        this.introduct = introduct;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }
}
