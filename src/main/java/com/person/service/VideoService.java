package com.person.service;

import com.person.bean.Menu;

import java.util.List;

public interface VideoService {
    public List<Menu> findVideo(String id);
}
