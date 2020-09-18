package com.person.bean;

public class JobTrade {
    private Integer id;
    private Integer jobid;
    private Integer tradeid;
    private String state;
    private String job;
    private String trade;
    private Integer num;
    public JobTrade() {
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

    public Integer getTradeid() {
        return tradeid;
    }

    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "JobTrade{" +
                "id=" + id +
                ", jobid=" + jobid +
                ", tradeid=" + tradeid +
                ", state='" + state + '\'' +
                ", job='" + job + '\'' +
                ", trade='" + trade + '\'' +
                ", num=" + num +
                '}';
    }


}
