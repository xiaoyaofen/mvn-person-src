package com.person.mapper;

import com.person.bean.Menu;
import com.person.bean.Params;
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

     public void addScopeParams(@Param("scope")String scope);

     public String findMaxValue();
}
