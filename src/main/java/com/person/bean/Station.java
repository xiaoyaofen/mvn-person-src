package com.person.bean;

import java.util.Date;

public class Station {
    private Integer id;
    private String title;//招聘信息（岗位自定义）
    private Integer sex; //性别限制
    private String sexStr;
    private Integer education;//学历限制
    private String educationStr;
    private String region;//地区限制
    private  Integer experience;//经验限制
    private String experienceStr;
    private String money; //工作薪资
    private Date date;//发布日期
    private Integer adminid;//发布者
    private String adminname;//发布者账号
    private Integer jobid;//职位序号
    private String jobname;//职位
    private String worktime;//工作时间
    private String description;//职位描述
    private String welfare;//工作福利
    private String other;//其他
    private String number;//招聘人数
    private String job;//职位
    private Integer company;
    private String companyname;//公司

    private Integer pm;//序号

    private String trade;//行业
    private String request;
    private String jobexpress;
    private String people;
    private String time;

    private String address;



    public Station() { }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getSex() {
        return sex;
    }


    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }


    public Integer getEducation() {
        return education;
    }


    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getEducationStr() {
        return educationStr;
    }

    public void setEducationStr(String educationStr) {
        this.educationStr = educationStr;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getExperienceStr() {
        return experienceStr;
    }

    public void setExperienceStr(String experienceStr) {
        this.experienceStr = experienceStr;
    }


    public String getMoney() {
        return money;
    }


    public void setMoney(String money) {
        this.money = money;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public Integer getJobid() {
        return jobid;
    }

    public void setJobid(Integer jobid) {
        this.jobid = jobid;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWelfare() {
        return welfare;
    }

    public void setWelfare(String welfare) {
        this.welfare = welfare;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getJobexpress() {
        return jobexpress;
    }

    public void setJobexpress(String jobexpress) {
        this.jobexpress = jobexpress;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sex=" + sex +
                ", sexStr='" + sexStr + '\'' +
                ", education=" + education +
                ", educationStr='" + educationStr + '\'' +
                ", region='" + region + '\'' +
                ", experience=" + experience +
                ", experienceStr='" + experienceStr + '\'' +
                ", money='" + money + '\'' +
                ", date=" + date +
                ", adminid=" + adminid +
                ", adminname='" + adminname + '\'' +
                ", jobid=" + jobid +

                ", worktime='" + worktime + '\'' +
                ", description='" + description + '\'' +
                ", welfare='" + welfare + '\'' +
                ", other='" + other + '\'' +
                ", number='" + number + '\'' +
                ", job='" + job + '\'' +
                ", jobname='" + jobname + '\'' +
                ", pm=" + pm +
                ", request='" + request + '\'' +
                ", jobexpress='" + jobexpress + '\'' +
                ", people='" + people + '\'' +
                ", time='" + time + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", trade='" + trade + '\'' +
                '}';
    }
}
