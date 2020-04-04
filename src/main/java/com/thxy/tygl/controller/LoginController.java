package com.thxy.tygl.controller;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.service.AdminService;
import com.thxy.tygl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam
            String password, Map<String, Object> message,
                        HttpSession session, HttpServletResponse response,HttpServletRequest request) {
        String remember=null;
        remember=request.getParameter("remember");
//        System.out.println(remember);
        String state = loginService.checkAdmin(username, password);
        if (state.equals("1")) {
            //更新登录的时间
            loginService.updateLoginTime(username);
            //创建cookie
            if("on".equals(remember)) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(3*24*60*60);
                cookie.setPath("/");
                response.addCookie(cookie);
            }else {}
            //防止重复提交重定向
            Admin admin=adminService.checkAdminByUserName(username);
            session.setAttribute("admin", admin);
            return "redirect:Index.html";
        } else {
            message.put("mes", "账号密码有误");
            return "Login";
        }

    }


    /**
     * 从request中检查是否携带用户已经登录过的cookie，
     * 若有则免登录直接到首页，无则返回登录页面
     *
     *
     * @param request
     * @return
     */
    @RequestMapping("/")
    public String checckCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String checkCookie = null;

        if(cookies!=null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    checkCookie = c.getName();
                    break;
                }
            }
        }
        if(checkCookie!=null)
            return "Index";
        else
            return "Login";


    }

    @RequestMapping("/changeUser")
    public String changUser(){

        return "redirect:Login.html";
    }
}