package com.person.mapper;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoMapper {
    public List<Menu> findVideo(@Param("id") String id);

    public String findUrlById(@Param("id")String id);

    public List<Product> findProduct();

    public Product findProductOne(@Param("id")String id);

    public Charpter findCharpterByid(@Param("id")String id);
}
