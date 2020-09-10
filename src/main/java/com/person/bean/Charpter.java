package com.person.bean;

public class Charpter {
     private Integer id;
     private String menuname;
     private String name;
     private Integer pm;
     private String classtime;
     private String detial;
     private String url;
     private String menuname1;
     private String scope;
     private Integer state;

    public Charpter() {
    }

    public Charpter(Integer id, String menuname, String name, Integer pm, String classtime, String detial, String url, String menuname1, String scope, Integer state) {
        this.id = id;
        this.menuname = menuname;
        this.name = name;
        this.pm = pm;
        this.classtime = classtime;
        this.detial = detial;
        this.url = url;
        this.menuname1 = menuname1;
        this.scope = scope;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public String getClasstime() {
        return classtime;
    }

    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuname1() {
        return menuname1;
    }

    public void setMenuname1(String menuname1) {
        this.menuname1 = menuname1;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
