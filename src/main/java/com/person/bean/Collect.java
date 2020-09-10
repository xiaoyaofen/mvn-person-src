package com.person.bean;

public class Collect {
    private Integer id;
    private  String industry;
    private String promulgator;
    private  String post;
    private  String time;
    public Collect(){

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

    public String getPromulgator() {
        return promulgator;
    }

    public void setPromulgator(String promulgator) {
        this.promulgator = promulgator;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Collect(Integer id, String industry, String promulgator, String post, String time) {
        this.id = id;
        this.industry = industry;
        this.promulgator = promulgator;
        this.post = post;
        this.time = time;
    }
}
