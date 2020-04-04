package com.thxy.tygl.mapper;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {
    Admin checkAdmin(@Param("username") String username,@Param("password") String password);
    //分页查询
    List<Admin> getAllAdminForPage(@Param("currPage") int nowPage, @Param("pageSize") int pageSize);
    //数据量查询
    int  getCountAdmin();
    //查找用户
    List<Admin> findAdminByName(@Param("param") Map<String,Object> map);

    //查找到的用户总量
    int countFindAdmin(@Param("name") String name);

    //新增管理员
    int addAdmin(Admin admin);

    //通过username查找管理员
    Admin checkAdminByName(@Param("username") String username);

    //更新管理员登录时间

    void updateLoginTime(@Param("username") String username , @Param("time") String time);

    //更新管理员信息
    int updateAdmin(Admin admin);


//    //查找用户
//    List<Notice> findAdminByTitle(@Param("param") Map<String,Object> map);
}
