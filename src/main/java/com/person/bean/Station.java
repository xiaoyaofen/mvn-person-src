package com.person.bean;

public class Station {
    private Integer id;
    private Integer jobid;
    private String request;
    private String money;
    private String jobexpress;
    private String people;
    private String time;
    private String company;
    private String welfare;
    private String address;

    public Station() {
    }

    public Station(Integer id, Integer jobid, String request, String money, String jobexpress, String people, String time, String company, String welfare, String address) {
        this.id = id;
        this.jobid = jobid;
        this.request = request;
        this.money = money;
        this.jobexpress = jobexpress;
        this.people = people;
        this.time = time;
        this.company = company;
        this.welfare = welfare;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getJobexpress() {
        return jobexpress;
    }

    public void setJobexpress(String jobexpress) {
        this.jobexpress = jobexpress;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
