package com.person.bean;

public class Product {
    private Integer pm;
    private Integer id;
    private String product;
    private String start;
    private String over;
    private String publisher;
    private String teacher;
    private String scope;
    private String picture;
    private String detial;
    private String state;
    private String productid;
    private String time;

    public Product(){}

    public Product(Integer pm, Integer id, String product, String start, String over, String publisher, String teacher, String scope, String picture, String detial, String state, String productid, String time) {
        this.pm = pm;
        this.id = id;
        this.product = product;
        this.start = start;
        this.over = over;
        this.publisher = publisher;
        this.teacher = teacher;
        this.scope = scope;
        this.picture = picture;
        this.detial = detial;
        this.state = state;
        this.productid = productid;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
