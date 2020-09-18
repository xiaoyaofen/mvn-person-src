package com.person.service;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Product;

import java.util.List;

public interface VideoService {
    public List<Menu> findVideo(String id);

    public String findUrlById(String id);

    public List<Product> findProduct();

    public Product findProductOne(String id);

    public Charpter findCharpterByid(String id);
}
