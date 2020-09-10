package com.person.bean;

public class Admin {

    private Integer id;       //id
    private String account;   //账号
    private String pwd;       //密码
    private String tel;       //手机号
    private String address;   //地址
    private Integer company;  //公司
    private Integer roleid;  //职位
    private String state;     //状态
    private String money;     //余额

    public Admin(Integer id, String account, String pwd, String tel, String address, Integer company, Integer role_id, String state, String money) {
        this.id = id;
        this.account = account;
        this.pwd = pwd;
        this.tel = tel;
        this.address = address;
        this.company = company;
        this.roleid = roleid;
        this.state = state;
        this.money = money;
    }

    public Admin(){

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

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getRole_id() {
        return roleid;
    }

    public void setRole_id(Integer role_id) {
        this.roleid = role_id;
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
}
