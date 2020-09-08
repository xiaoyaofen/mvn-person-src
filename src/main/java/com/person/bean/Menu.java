package com.person.bean;
public class Menu {
    private Integer id;
    private String scope;
    private String menuname;
    private String upmenu;

    public Menu() {
    }

    public Menu(Integer id, String scope, String menuname, String upmenu) {
        this.id = id;
        this.scope = scope;
        this.menuname = menuname;
        this.upmenu = upmenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getUpmenu() {
        return upmenu;
    }

    public void setUpmenu(String upmenu) {
        this.upmenu = upmenu;
    }
}
