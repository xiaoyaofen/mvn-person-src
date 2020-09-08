package com.person.bean;

public class Params {
    private Integer id;
    private String type;
    private String name;
    private String value;
    private String state;

    public Params() {
    }

    public Params(Integer id, String type, String name, String value, String state) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.value = value;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
