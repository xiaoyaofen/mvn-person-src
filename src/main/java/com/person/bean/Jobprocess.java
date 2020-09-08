package com.person.bean;

public class Jobprocess {
    private Integer id;
    private String one;
    private String two;
    private String three;
    private String four;
    private Integer companyid;
    private Integer jobid;

    public Jobprocess() {
    }

    public Jobprocess(Integer id, String one, String two, String three, String four, Integer companyid, Integer jobid) {
        this.id = id;
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        this.companyid = companyid;
        this.jobid = jobid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }
}
