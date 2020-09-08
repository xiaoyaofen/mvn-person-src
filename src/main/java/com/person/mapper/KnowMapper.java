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

     public Integer findCount();

     public List<Params> addselect();
}
