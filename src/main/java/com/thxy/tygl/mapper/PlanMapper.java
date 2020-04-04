package com.thxy.tygl.mapper;

import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.entity.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {
    List<Plan> getAllPlanForPage(@Param("currPage") int nowPage, @Param("pageSize") int pageSize);
    int  getCountPlan();
    void addPlan(@Param("map") Map<String,Object> map);
    //查找图片
    List<Plan> findPlanByDate(@Param("param") Map<String,Object> map);

    //查找到的图片总量
    int countFindPlan(@Param("date") String date);

    //根据id更改其状态信息
    int delPlanById(@Param("planId") int param);
}
