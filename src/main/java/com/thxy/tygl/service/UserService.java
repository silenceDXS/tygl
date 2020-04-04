package com.thxy.tygl.service;

import com.thxy.tygl.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String,Object> findAllUser(int nowPage, int pageSize);
    public  int getTotal();
    public  List<User> findUser(int nowPage,int pageSize,int param);
    public  int  countFindUser( int studentId);
    int delUserById( int param);
}
