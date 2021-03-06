package com.person.bean;

public class Role {
    private Integer id;
    private String name;
    private String type;
    private Integer value;
    private Integer pm;
    public Role() {
    }

    public Role(Integer id, String name, String type, Integer value, Integer pm) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.value = value;
        this.pm = pm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", pm=" + pm +
                '}';
    }
}
