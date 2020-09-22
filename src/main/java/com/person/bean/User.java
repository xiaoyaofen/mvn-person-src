package com.person.bean;


import java.util.Date;

public class User{

    private Integer id;
    private String account;
    private String pwd;
    private String name;
    private Integer sex;
    private Integer age;
    private String tel;
    private String address;
    private String state;
    private String money;
    private Date date;
    private String jobstate;
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
    private String img; //用户头像
    private String email;

    private  String project;//项目经验
    private String educationbackground;//教育背景
    private String self;//自我描述


    private String username;//人脸识别的名字
    private  String faceId;//人脸识别的id

    public User() {
    }

    public User(Integer id, String account, String pwd, String name, Integer sex, Integer age, String tel, String address, String state, String money, Date date, String jobstate, Date birthday, String specialty, Integer schoolId, String schoolName, Integer resume, Date brith, Integer education, Integer exprience, String major, String school, String outlook) {
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

    public User(Integer id, String account, String pwd, String name, Integer sex, Integer age, String tel, String address, String state, String money, Date date, String jobstate, Date birthday, String specialty, Integer schoolId, String schoolName, Integer resume, Date brith, Integer education, Integer exprience, String major, String school, String outlook, String img) {
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
        this.img = img;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getEducationbackground() {
        return educationbackground;
    }

    public void setEducationbackground(String educationbackground) {
        this.educationbackground = educationbackground;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
}

