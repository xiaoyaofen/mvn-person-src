package com.person.bean;


import java.util.Date;

public class User{

    private Integer id;
    private String account;
    private String pwd;
    private String name;
    private Integer sex;
    private Integer age;
    private String tel;
    private String address;
    private String state;
    private String money;
    private Date date;
    private String jobstate;

    public User() {
    }

    public User(Integer id, String account, String pwd, String name, Integer sex, Integer age, String tel, String address, String state, String money, Date date, String jobstate) {
        this.id = id;
        this.account = account;
        this.pwd = pwd;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.address = address;
        this.state = state;
        this.money = money;
        this.date = date;
        this.jobstate = jobstate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getJobstate() {
        return jobstate;
    }

    public void setJobstate(String jobstate) {
        this.jobstate = jobstate;
    }
}

