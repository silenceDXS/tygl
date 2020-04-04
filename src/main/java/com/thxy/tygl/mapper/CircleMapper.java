package com.thxy.tygl.mapper;

import com.thxy.tygl.entity.Circle;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CircleMapper {
    //分页查询
    List<Circle> getAllCircleForPage(@Param("currPage") int nowPage, @Param("pageSize") int pageSize);
    //数据量查询
    int  getCountCircle();
    //查找用户
    List<Circle> findCircleByName(@Param("param") Map<String,Object> map);

    //查找到的用户总量
    int countFindCircle(@Param("studentId") int studentId);
    //根据学号删除账号信息
    int delCircleByStudentId(@Param("studentId") int param);

    Circle findCircleByCircleId(@Param("circleId") int circleId);
}
