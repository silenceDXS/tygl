package com.thxy.tygl.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.thxy.tygl.entity.User;
import com.thxy.tygl.service.UserService;
import com.thxy.tygl.untils.PageResult;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    /**
     *  获取所有用户信息
     */

    @Autowired
    UserService userService;
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    public String getAllUsers (Model model, HttpServletRequest request){
        String param=request.getParameter("param");
        String pagenum=request.getParameter("nowPage");
        int nowPage=Integer.parseInt(pagenum);
        int pageSize=5;
        Map<String,Object> resultMap=userService.findAllUser(nowPage,pageSize);
        int total=userService.getTotal();
        PageResult pageResult=new PageResult(total,pageSize,nowPage);
        pageResult.setFindParam(1);
        model.addAttribute("user",resultMap.get("users"));
        model.addAttribute("pageResult",pageResult);
        if(param.equals("1"))
            return  "User";
        else {
            return "User::pageUser";
        }
    }

    /**
     * 根据用户学号查询信息
     * @param request
     * @param model
     * @return
     */

    @RequestMapping("/findUser")
    public String  findUser(HttpServletRequest request, Model model){
        String param=request.getParameter("param");
        String pageNum=request.getParameter("nowPage");
        int param1=Integer.parseInt(param);
        List<User> user=userService.findUser(Integer.parseInt(pageNum),5,param1);
        int total=userService.countFindUser(param1);
        PageResult pageResult=new PageResult(total,5,Integer.parseInt(pageNum));
        pageResult.setFindParam(2);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("user", user);
        return "User::pageUser";

    }


    /**
     * 根据studentId删除用户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/delUser")
    public  String delUserById(HttpServletRequest request,Model model){

        String param=request.getParameter("param");
        String nowPage=request.getParameter("nowPage");
        int nowPage1=Integer.parseInt(nowPage);
        int param1= Integer.parseInt(param);
        int result=userService.delUserById(param1);
        int total=userService.getTotal();
        Map<String,Object> map=userService.findAllUser(nowPage1,5);
        PageResult pageResult=new PageResult(total,5,nowPage1);
        pageResult.setFindParam(1);
        model.addAttribute("user",map.get("users"));
        model.addAttribute("pageResult",pageResult);
        return  "User::pageUser";

    }
}
