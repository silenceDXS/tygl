package com.thxy.tygl.service;

public interface LoginService {
    public String checkAdmin(String username,String password);
    void updateLoginTime(String username);
}
