package com.thxy.tygl.service.ServiceImpl;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.mapper.AdminMapper;
import com.thxy.tygl.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Transactional
public class LoginServiceImpl  implements LoginService {
    @Autowired
    AdminMapper adminMapper;
    /**
     *
     * @param username
     * @param password
     * @return 账号密码正确return statte=="1"  错误return"0"
     */
    @Override
    public String checkAdmin(String username, String password) {
        String state = "0";
        Admin admin=adminMapper.checkAdmin(username,password);
        if(admin!=null){
            state="1";
        }else {

        }
        return state;
    }

    @Override
    public void updateLoginTime(String username) {
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(date);
        adminMapper.updateLoginTime(username,time);
    }
}