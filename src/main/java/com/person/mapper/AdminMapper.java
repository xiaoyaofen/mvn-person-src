package com.person.mapper;

import com.person.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Mapper
@Component
public interface AdminMapper {

    List<UserInfo> getUserByAdmin(@Param("condition") HashMap<String, Object> condition, @Param("limit") Integer limit, @Param("page") Integer page);

    Integer getUserByAdminOfNum(@Param("condition") HashMap<String, Object> condition);

    public List<Params> getParamList(@Param("start") Integer start,
                                     @Param("pageSize") Integer pageSize,
                                     @Param("name") String name,
                                     @Param("type") String type
    );
    public int getParamListCount(@Param("name") String name,
                                 @Param("type") String type

    );
    public List<String> paramsTypeList();

    public int addParams(@Param("name") String name,
                         @Param("type") String type,
                         @Param("value") String value
    );

    public int delParams(@Param("state") String state,
                         @Param("id") int id
    );


    public int editParams(@Param("name") String name,
                          @Param("id") int id

    );


    public List<Role> roleList();

    public int roleListCount();

    //批量导入学生信息========================================================
    public Integer uploadExcel(@Param("list") List<User> userInfoList);

    //高校人才推荐数据获取========获取总数========================================
    Integer userRecommendNum(@Param("condition") HashMap<String, Object> condition);
    //高校人才推荐数据获取=========获取信息=======================================
    List<Station> userRecommend(@Param("condition")HashMap<String, Object> condition, @Param("limit")Integer limit,@Param("page") Integer page);

    //高校人才推荐 ==============选择人才数据显示 ====== 总数
    Integer userSelectNum(@Param("condition")HashMap<String, Object> condition);
    //高校人才推荐 ==============选择人才数据显示 ======= 数据
    List<Station> userSelect(@Param("condition")HashMap<String, Object> condition,@Param("limit") Integer limit,@Param("page") Integer page);

    //高校人才推荐 ==============确定选择推荐人选 ====== 新增jobcontaion
    Integer userSelectSure(@Param("list") List<Integer> list, @Param("jobid") Integer jobid);
}
