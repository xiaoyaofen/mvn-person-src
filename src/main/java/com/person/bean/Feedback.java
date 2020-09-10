package com.person.bean;

public class Feedback {
    private Integer id;
    private String industry;
    private  String post;
    private  String firm;
    private String tel;
    private  String state;
    private  Integer userid;


    public Feedback(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Feedback(Integer id, String industry, String post, String firm, String tel, String state, Integer userid) {
        this.id = id;
        this.industry = industry;
        this.post = post;
        this.firm = firm;
        this.tel = tel;
        this.state = state;
        this.userid = userid;
    }
}
