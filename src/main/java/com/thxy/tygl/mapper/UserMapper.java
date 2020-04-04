package com.thxy.tygl.mapper;

import com.thxy.tygl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface UserMapper {
    //分页查询
     List<User> getAllUserForPage(@Param("currPage") int nowPage,@Param("pageSize") int pageSize);
    //数据量查询
    int  getCountUser();
    //查找用户
    List<User> findUser(@Param("currPage") int nowPage,@Param("pageSize") int pageSize,@Param("studentId") int param);

    //查找到的用户总量
    int countFindUser(@Param("studentId") int param);

    //根据学号删除账号信息
    int delUserById(@Param("studentId") int param);
}
