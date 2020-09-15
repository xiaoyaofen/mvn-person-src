package com.person.bean;

public class AD {
    private Integer id;
    private String url;
    private String ip;
    private String name;

    public AD() {

    }

    public AD(Integer id, String url, String ip, String name) {
        this.id = id;
        this.url = url;
        this.ip = ip;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
