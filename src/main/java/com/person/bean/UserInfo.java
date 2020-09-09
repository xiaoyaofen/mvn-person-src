package com.person.bean;

import java.util.Date;

public class UserInfo extends User {
    private Date birthday;//出生年份
    private String specialty;//学历
    private Integer schoolId; //学校id
    private String schoolName;// 学校名

    public UserInfo(){};

    public UserInfo(Integer id, String account, String pwd, String name, Integer sex, Integer age, String tel, String address, String state, String money, Date date, String jobstate, Date birthday, String specialty, Integer schoolId, String schoolName) {
        super(id, account, pwd, name, sex, age, tel, address, state, money, date, jobstate);
        this.birthday = birthday;
        this.specialty = specialty;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
