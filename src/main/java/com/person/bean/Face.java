package com.person.bean;

import org.apache.poi.openxml4j.opc.PackageRelationship;

public class Face {
    private  Integer  id;
    private  String group_id;
    private String face_id;
    private String name;
    private  String age;
    private  String email;
    private  String gender;
    private  String phone_number;
    private  String face_feature;
    private  String create_time;
    private  String update_time;
    private  String fpath;
    private  String username;//用户账号



    public Face(){

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public void setFace_id(String face_id) {
        this.face_id = face_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setFace_feature(String face_feature) {
        this.face_feature = face_feature;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Face{" +
                "id=" + id +
                ", group_id='" + group_id + '\'' +
                ", face_id='" + face_id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", face_feature='" + face_feature + '\'' +
                ", create_time='" + create_time + '\'' +
                ", update_time='" + update_time + '\'' +
                ", fpath='" + fpath + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}



