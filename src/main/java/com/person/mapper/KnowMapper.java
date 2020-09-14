package com.person.mapper;

import com.person.bean.Charpter;
import com.person.bean.Menu;
import com.person.bean.Params;
import com.person.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Mapper
@Component
public interface KnowMapper {
     public List<Menu> getKnowMsg(@Param("page") Integer page, @Param("limit") Integer limit,@Param("title") String title,@Param("scope") String scope);

     public Integer findCount(@Param("title")String title,@Param("scope")String scope);

     public List<Params> addselect();

     public Integer delKnow(@Param("id") String id);

     public String findScope(@Param("scope")String scope);

     public Integer addKnow(@Param("pid")String pid,@Param("menuname")String menuname,@Param("scope")String scope);

     public Integer fixKnow(@Param("id")String id,@Param("scope")String scope,@Param("menuname")String menuname);

     public Menu findCourse(@Param("scope")String scopeId,@Param("menuname")String menuname);

     public Params findScopeParmas(@Param("scope")String scope);

     public Integer addScopeParam(@Param("scope")String scope,@Param("maxValue")String maxValue);

     public String findMaxValue();

     public Integer addKnowmenu(@Param("menuname")String menuname,@Param("detial")String detial,@Param("value")String value);

     public List<Character> findCharpter(@Param("page") Integer page, @Param("limit") Integer limit,@Param("title") String title,@Param("scope") String scope);

     public Integer findCharpterCount(@Param("title")String title, @Param("scope")String scope);

     public Integer delCharpter(@Param("id")String id);

     public Charpter seeCharpter(@Param("id")String id);

     public void setCharpter(@Param("id")String id, @Param("introduce")String introduce, @Param("classTime")String classTime, @Param("url")String url);

     public String findUrl(@Param("id")String id);

     public List<Product> findProduct(@Param("page") Integer page, @Param("limit") Integer limit, @Param("scope") String scope,@Param("state") String state);

     public Integer findProductCount(@Param("scope")String scope,@Param("state")String state);

     public Integer changeState(@Param("id") String id, @Param("state") String state);

     public List<Params> teacherSel();
}
