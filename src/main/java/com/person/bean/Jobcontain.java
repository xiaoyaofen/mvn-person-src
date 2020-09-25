package com.person.bean;

//招聘关联表
public class Jobcontain {
    private Integer id;
    private Integer userid;//学生
    private Integer jobprocessid;
    private Integer stateid;//招聘信息
    private String one;//
    private String two;
    private String three;
    private String four;
    private String company;//公司名
    private String address;//公司地址
    private String username; ///用户名
    private String email;//用户email

    public Jobcontain() {
    }

    public Jobcontain(Integer id, Integer userid, Integer jobprocessid, Integer stateid, String one, String two, String three, String four, String company, String address, String username, String email) {
        this.id = id;
        this.userid = userid;
        this.jobprocessid = jobprocessid;
        this.stateid = stateid;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.company = company;
        this.address = address;
        this.username = username;
        this.email = email;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
