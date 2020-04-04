package com.thxy.tygl.service;

import com.thxy.tygl.entity.Circle;

import java.util.List;
import java.util.Map;

public interface CircleService {
    public Map<String,Object> findAllCircle(int nowPage, int pageSize);
    public  int getTotal();
    public List<Circle> findCircleByStudentId(int nowPage, int pageSize, String StudentId);
    public  int  countFindCircle( String StudentId);
    int delCircleByStudentId( int param);
    Circle findCircleByCircleId(int circleId);
}
