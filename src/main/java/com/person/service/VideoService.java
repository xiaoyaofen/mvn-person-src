package com.person.service;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Product;
import com.person.bean.Talk;

import java.util.List;

public interface VideoService {
    public List<Menu> findVideo(String id);

    public String findUrlById(String id);

    public List<Product> findProduct();

    public Product findProductOne(String id);

    public Charpter findCharpterByid(String id);

    public List<Talk> findTalk();

    public Integer findTalkCount();

    public Integer addMessage(String message,String userid);

    public List<Product> studyProduct(String userid);

    public void updateStudyTime(String userid,String productid);

    public Product hasStudy(String userid,String productid);

    public void addStudy(String userid,String productid);
}
