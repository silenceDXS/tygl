package com.thxy.tygl.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerIntercetor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception{
        Object username=request.getSession().getAttribute("admin");
        if(username==null){
            //未登录
            request.setAttribute("mes","没有权限，先登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }else{
            return  true;
        }

    }



}
