package com.thxy.tygl.service;

import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.entity.Plan;

import java.util.List;
import java.util.Map;

public interface PlanService {
    Map<String,Object> getAllPlan(int nowPage,int pageSize);
    void addPlan(String pic);
    int getTotal();
    public List<Plan> findPlanByDate(int nowPage, int pageSize, String date);
    public  int  countFindPlan( String title);
    int delPlanById(int param);
}
