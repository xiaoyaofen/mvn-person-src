package com.person.service.impl;

import com.person.bean.Menu;
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
}
