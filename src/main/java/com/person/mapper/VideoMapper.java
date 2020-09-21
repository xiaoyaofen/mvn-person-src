package com.person.mapper;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Product;
import com.person.bean.Talk;
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

    public List<Talk> findTalk();

    public Integer findTalkCount();

    public Integer addMessage(@Param("message")String message,@Param("userid")String userid);

    public List<Product> studyProduct(@Param("userid")String userid);

    public void updateStudyTime(@Param("userid")String userid,@Param("productid")String productid);

    public Product hasStudy(@Param("userid")String userid,@Param("productid")String productid);

    public void addStudy(@Param("userid")String userid,@Param("productid")String productid);
}
