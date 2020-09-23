package com.person.service.impl;

import com.person.aoplog.Log;
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
    @Log(operationType = "根据id查视频", operationName = "user")
    public List<Menu> findVideo(String id) {
        List<Menu> list=videoMapper.findVideo(id);
        return list;
    }

    @Override
    @Log(operationType = "根据id查视频路径", operationName = "user")
    public String findUrlById(String id) {
        String url=videoMapper.findUrlById(id);
        return url;
    }

    @Override
    @Log(operationType = "技术成长首页", operationName = "user")
    public List<Product> findProduct() {
        List<Product> list=videoMapper.findProduct();
        return list;
    }

    @Override
    @Log(operationType = "具体课程", operationName = "user")
    public Product findProductOne(String id) {
        Product product=videoMapper.findProductOne(id);
        return product;
    }

    @Override
    @Log(operationType = "根据id查找播放视频", operationName = "user")
    public Charpter findCharpterByid(String id) {
        Charpter charpter=videoMapper.findCharpterByid(id);
        return charpter;
    }

    @Override
    @Log(operationType = "查找评论", operationName = "user")
    public List<Talk> findTalk() {
        List<Talk> list=videoMapper.findTalk();
        return list;
    }

    @Override
    @Log(operationType = "查找评论记录数", operationName = "user")
    public Integer findTalkCount() {
        Integer count=videoMapper.findTalkCount();
        return count;
    }

    @Override
    @Log(operationType = "增加评论", operationName = "user")
    public Integer addMessage(String message, String userid) {
        Integer num=videoMapper.addMessage(message,userid);
        return num;
    }

    @Override
    @Log(operationType = "课程学习", operationName = "user")
    public List<Product> studyProduct(String userid) {
        List<Product> list=videoMapper.studyProduct(userid);
        return list;
    }

    @Override
    @Log(operationType = "跟新学习时长", operationName = "user")
    public void updateStudyTime(String userid,String productid) {
        videoMapper.updateStudyTime(userid,productid);
    }

    @Override
    @Log(operationType = "已学习", operationName = "user")
    public Product hasStudy(String userid,String productid) {
        Product product=videoMapper.hasStudy(userid,productid);
        return product;
    }

    @Override
    @Log(operationType = "增加学习", operationName = "user")
    public void addStudy(String userid, String productid) {

    }
}
