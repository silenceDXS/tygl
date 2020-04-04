package com.thxy.tygl.service;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.entity.AdminJson;
import com.thxy.tygl.entity.Notice;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AdminService {
    public Map<String,Object> findAllAdmin(int nowPage, int pageSize);
    public  int getTotal();
    public List<Admin> findAdminByName(int nowPage, int pageSize, String title);
    public  int  countFindAdmin( String name);
    int addAdmin(AdminJson adminJson);
    Admin checkAdminByUserName(String username);
    int updateAdmin(String password, HttpServletRequest request);
}
