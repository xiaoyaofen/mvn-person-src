package com.person.service.impl;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Product;
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
}
