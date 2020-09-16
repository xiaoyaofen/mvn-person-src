package com.person.bean;

public class Company {
    private Integer id;
    private String trade;
    private String type;
    private String people;
    private String address;
    private String name;
    private String introduce;
    private String tel;
    private String href;
    private String business;

    public Company() {
    }

    public Company(Integer id, String trade, String type, String people, String address, String name, String introduce, String tel, String href, String business) {
        this.id = id;
        this.trade = trade;
        this.type = type;
        this.people = people;
        this.address = address;
        this.name = name;
        this.introduce = introduce;
        this.tel = tel;
        this.href = href;
        this.business = business;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
