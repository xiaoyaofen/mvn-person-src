package com.person.bean;


import java.util.Date;

public class User{

    private Integer id;
    private String account;
    private String pwd;
    private String name;
    private Integer sex;
    private String sexStr;
    private Integer age;
    private String tel;
    private String address;
    private String state;
    private String money;//充值金额
    private Date date;//注册时间
    private String jobstate;//就业状况
    private Date resgisdata;///简历注册时间
    private Date birthday;//出生年份
    private String specialty;//学历
    private Integer schoolId; //学校id
    private String schoolName;// 学校名

    private Integer resume; //简历模板
    private Date brith;//出生年月
    private Integer education;//最高学历
    private Integer exprience;//工作经验
    private String major;//专业
    private String school;//学校
    private String outlook;//社会面貌
    private String recommend;//推荐者
    private String title;//职位
    private Integer jobstation;//关系表id
    private Integer pm;//序号
    private Integer num;  //序号

    public User() {
    }

    public User(Integer id, String account, String pwd, String name, Integer sex, Integer age, String tel, String address, String state, String money, Date date, String jobstate) {
        this.id = id;
        this.account = account;
        this.pwd = pwd;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.address = address;
        this.state = state;
        this.money = money;
        this.date = date;
        this.jobstate = jobstate;
        this.birthday = birthday;
        this.specialty = specialty;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.resume = resume;
        this.brith = brith;
        this.education = education;
        this.exprience = exprience;
        this.major = major;
        this.school = school;
        this.outlook = outlook;
    }

    public Integer getPm() {
        return pm;
    }

    public void setPm(Integer pm) {
        this.pm = pm;
    }

    public Integer getJobstation() {
        return jobstation;
    }

    public void setJobstation(Integer jobstation) {
        this.jobstation = jobstation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getJobstate() {
        return jobstate;
    }

    public void setJobstate(String jobstate) {
        this.jobstate = jobstate;
    }

    public Integer getResume() {
        return resume;
    }

    public void setResume(Integer resume) {
        this.resume = resume;
    }

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public Integer getExprience() {
        return exprience;
    }

    public void setExprience(Integer exprience) {
        this.exprience = exprience;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getOutlook() {
        return outlook;
    }

    public void setOutlook(String outlook) {
        this.outlook = outlook;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Date getResgisdata() {
        return resgisdata;
    }

    public void setResgisdata(Date regisdata) {
        this.resgisdata = regisdata;
    }
}

