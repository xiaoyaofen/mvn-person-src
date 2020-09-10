package com.person.bean;

public class Params {
    private Integer id;
    private String type;
    private String name;
    private String value;
    private String state;
    private Integer pm;
    public Params() {
    }

    public Params(Integer id, String type, String name, String value, String state, Integer pm) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.value = value;
        this.state = state;
        this.pm = pm;
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

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Params{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", state='" + state + '\'' +
                ", pm=" + pm +
                '}';
    }
}
