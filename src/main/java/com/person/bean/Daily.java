package com.person.bean;

public class Daily {
    private Integer id;
    private String event;
    private String time;
    private Integer worker;
    private  String operation;
    private Integer num;
    public Daily() {
    }

    public Daily(Integer id, String event, String time, Integer worker, String operation,Integer num) {
        this.id = id;
        this.event = event;
        this.time = time;
        this.worker = worker;
        this.operation = operation;
        this.num=num;
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


    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Daily{" +
                "id=" + id +
                ", event='" + event + '\'' +
                ", time='" + time + '\'' +
                ", worker=" + worker +
                ", operation='" + operation + '\'' +
                ", num=" + num +
                '}';
    }
}
