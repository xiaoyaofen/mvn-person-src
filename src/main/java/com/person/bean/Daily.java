package com.person.bean;

public class Daily {
    private Integer id;
    private String event;
    private String time;
    private Integer worker;

    public Daily() {
    }

    public Daily(Integer id, String event, String time, Integer worker) {
        this.id = id;
        this.event = event;
        this.time = time;
        this.worker = worker;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getWorker() {
        return worker;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }
}
