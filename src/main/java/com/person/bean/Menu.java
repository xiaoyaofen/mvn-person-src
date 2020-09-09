package com.person.bean;
public class Menu {
    private Integer id;
    private String scope;
    private String menuname;
    private String upmenu;
    private String url;
    private Integer pid;
    private Integer mid;
    private Integer roleid;
    private Integer state;
    private String detial;

    public Menu() {
    }

    public Menu(Integer id, String scope, String menuname, String upmenu, String url, Integer pid, Integer mid, Integer roleid, Integer state, String detial) {
        this.id = id;
        this.scope = scope;
        this.menuname = menuname;
        this.upmenu = upmenu;
        this.url = url;
        this.pid = pid;
        this.mid = mid;
        this.roleid = roleid;
        this.state = state;
        this.detial = detial;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }
}
