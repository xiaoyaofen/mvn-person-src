package com.person.service.impl;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Product;
import com.person.bean.Talk;
import com.person.mapper.VideoMapper;
import com.person.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Menu> findVideo(String id) {
        List<Menu> list=videoMapper.findVideo(id);
        return list;
    }

    @Override
    public String findUrlById(String id) {
        String url=videoMapper.findUrlById(id);
        return url;
    }

    @Override
    public List<Product> findProduct() {
        List<Product> list=videoMapper.findProduct();
        return list;
    }

    @Override
    public Product findProductOne(String id) {
        Product product=videoMapper.findProductOne(id);
        return product;
    }

    @Override
    public Charpter findCharpterByid(String id) {
        Charpter charpter=videoMapper.findCharpterByid(id);
        return charpter;
    }

    @Override
    public List<Talk> findTalk() {
        List<Talk> list=videoMapper.findTalk();
        return list;
    }

    @Override
    public Integer findTalkCount() {
        Integer count=videoMapper.findTalkCount();
        return count;
    }

    @Override
    public Integer addMessage(String message, String userid) {
        Integer num=videoMapper.addMessage(message,userid);
        return num;
    }

    @Override
    public List<Product> studyProduct(String userid) {
        List<Product> list=videoMapper.studyProduct(userid);
        return list;
    }

    @Override
    public void updateStudyTime(String userid,String productid) {
        videoMapper.updateStudyTime(userid,productid);
    }

    @Override
    public Product hasStudy(String userid,String productid) {
        Product product=videoMapper.hasStudy(userid,productid);
        return product;
    }

    @Override
    public void addStudy(String userid, String productid) {

    }
}
