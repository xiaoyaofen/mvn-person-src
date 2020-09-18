package com.person.mapper;

import com.person.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Mapper
@Component
public interface SystemMapper {

    //参数列表
    public List<Params> getParamList(@Param("start") Integer start,
                                     @Param("pageSize") Integer pageSize,
                                     @Param("name") String name,
                                     @Param("type") String type
    );

    //参数记录数
    public int getParamListCount(@Param("name") String name,
                                 @Param("type") String type

    );

    //参数类型
    public List<String> paramsTypeList();

    //检测参数名
    public Params checkParams(@Param("name") String name,
                              @Param("type") String type
    );

    //增加参数
    public int addParams(@Param("name") String name,
                         @Param("type") String type,
                         @Param("value") String value
    );

    //删除参数
    public int delParams(@Param("state") String state,
                         @Param("id") int id
    );

    //修改参数
    public int editParams(@Param("name") String name,
                          @Param("id") int id

    );

    //角色列表
    public List<Role> roleList();

    //角色记录数
    public int roleListCount();

    //权限（不根据角色）
    public List<Menu> permission(@Param("id") int id);

    //权限（根据角色）
    public List<Menu> rightByRoleId(@Param("id") int id, @Param("roleId") int roleId);

    //删除角色权限
    public int delRight(@Param("roleId") int roleId);

    //修改角色权限
    public int updateRight(@Param("id") int id, @Param("roleId") int roleId);

    //管理员操作日志
    public List<Daily> adminLog(@Param("start") Integer start,
                                @Param("pageSize") Integer pageSize,
                                @Param("startDate") String startDate,
                                @Param("endDate") String endDate

    );

    //管理员日志记录数
    public int adminLogCount(@Param("startDate") String startDate,
                             @Param("endDate") String endDate

    );


    //用户操作日志
    public List<Daily> userLog(@Param("start") Integer start,
                               @Param("pageSize") Integer pageSize,
                               @Param("startDate") String startDate,
                               @Param("endDate") String endDate

    );

    //用户日志记录数
    public int userLogCount(@Param("startDate") String startDate,
                            @Param("endDate") String endDate

    );

    //用户管理列表
    public List<User> userList(@Param("start") Integer start,
                               @Param("pageSize") Integer pageSize,
                               @Param("school") String school,
                               @Param("state") String state
    );

    public int userLitCount(@Param("school") String school,
                            @Param("state") String state

    );

    //禁用/启用用户
    public int userManager(@Param("id") Integer id,
                           @Param("state") String state
    );

    public Admin adminLogIn(@Param("account") String account,
                            @Param("pwd") String pwd

    );

    public List<Role> findRole();

    public Admin checkAccount(@Param("account") String account);

    public Company selectCompany(@Param("name") String name);

    public Integer addCompany(@Param("company") Company company);


    public School selectSchool(@Param("name") String name);

    public Integer addSchool(@Param("School") School school);

    public Integer addAdmin(@Param("account") String account,
                            @Param("pwd") String pwd,
                            @Param("name") String name,
                            @Param("tel") String tel,
                            @Param("address") String address,
                            @Param("company") Integer company,
                            @Param("roleid") Integer roleid
    );


    public List<Params> selectTrade();

    public List<JobTrade> postManager(@Param("trade") String trade,
                                      @Param("start") Integer start,
                                      @Param("pageSize") Integer pageSize
    );

    public int postManagerCount(@Param("trade") String trade);

    public int delJob(@Param("state") String state,
                      @Param("id") Integer id
    );


    public Params checkJob(@Param("name") String name);

    public String maxJob();

    public int addJob(@Param("value") Integer value,
                      @Param("name") String name
    );


    public JobTrade checkJobTrade(@Param("jobid") Integer jobid,
                                  @Param("tradeid") Integer tradeid
    );


    public int addJobTrade(@Param("jobid") Integer jobid,
                           @Param("tradeid") Integer tradeid
    );

    public int editJobTrade(@Param("jobid") Integer jobid,
                            @Param("tradeid") Integer tradeid,
                            @Param("id") Integer id
    );




    public List<CompanyCheck> checkCompanyList(@Param("start") Integer start,
                                               @Param("pageSize") Integer pageSize,
                                               @Param("companyName") String companyName,
                                               @Param("state") String state

    );

    public int checkCompanyListCount(@Param("companyName") String companyName,
                                     @Param("state") String state

    );


    public  int checkCompany(@Param("state") String state,
                             @Param("id") Integer id);


}
