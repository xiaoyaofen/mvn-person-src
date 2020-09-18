package com.person.mapper;

import com.person.bean.Menu;
import com.person.bean.Params;
import com.person.bean.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface JobMapper {
    public List<Params> findJobMenu();

    public List<Params> findJobMenuTwo(@Param("id")Integer id);

    public List<Station> findStation(@Param("value")String value);

    public Station findOneStation(@Param("id")String id);
}
