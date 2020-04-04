package com.thxy.tygl.controller;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.entity.AdminJson;
import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.service.AdminService;
import com.thxy.tygl.untils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对管理员管理的所有CRUD进行拦截并进行处理
 */
@Controller
public class AdminController {
    

   /**
   * @Description:  (根据当前页数对管理员进行分页查询，参数(页码:nowPage;跳转类型:param))
   * @Param:  [nowPage,param]
   * @return:  String
   * @Author: silence
   * @Date: 2020/4/4
   */
    @Autowired
    AdminService adminService;
    @RequestMapping("/getAllAdmin")
    public  String  getAllAdmin(HttpServletRequest request, Model model){
        String pagenum=request.getParameter("nowPage");
        String param=request.getParameter("param");
        int nowPage=Integer.parseInt(pagenum);
        int pageSize=5;
        Map<String,Object> resultMap=adminService.findAllAdmin(nowPage,pageSize);
        int total= adminService.getTotal();
        PageResult pageResult=new PageResult(total,pageSize,nowPage);
        pageResult.setFindParam(1);
        model.addAttribute("admin",resultMap.get("admin"));
        model.addAttribute("pageResult",pageResult);
        if(param.equals("1"))   //param==1就跳转到Admin.html其他就进行模块化
            return  "Admin";
        else {
            return "Admin::pageAdmin";
        }
    }

    /**
    * @Description:  (根据当前页码和管理员真实姓名查找改管理员，并附加分页信息，参数(页码:nowPage;姓名；param))
    * @Param:  [nowPage,param]
    * @return:  String
    * @Author: silence
    * @Date: 2020/4/4
    */

    @RequestMapping("/findAdmin")
    public String  findAdmin(HttpServletRequest request, Model model){
        String param=request.getParameter("param");
        String pageNum=request.getParameter("nowPage");
        List<Admin> admin=adminService.findAdminByName(Integer.parseInt(pageNum),5,param);
        int total=adminService.countFindAdmin(param);
        PageResult pageResult=new PageResult(total,5,Integer.parseInt(pageNum));
        pageResult.setFindParam(2);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("admin", admin);
        return "Admin::pageAdmin";

    }

    /**
    * @Description:  (根据提交上来的管理员信息，添加管理员,参数[信息:adminJson])
    * @Param:  [adminJson]
    * @return:  Map[String,String]
    * @Author: silence
    * @Date: 2020/4/4
    */
    @RequestMapping("/addAdmin")
    @ResponseBody
    public Map<String,String> addAdmin(AdminJson adminJson){
        Map<String,String> map=new HashMap<>();
        Admin admin=adminService.checkAdminByUserName(adminJson.getUsername());
        if(admin==null){
            int result=adminService.addAdmin(adminJson);
            if(result==1){
                map.put("mes","1");
                return map;
            }else {
                map.put("mes","2");
            }
        }else {

                map.put("mes","0");
                return map;

        }
        map.put("mes","失败");
        return  map;
    }

    /**
    * @Description:  (根据管理员session,查找改管理员，参数(session:request))
    * @Param:  [request]
    * @return:  String
    * @Author: silence
    * @Date: 2020/4/4
    */
    @RequestMapping("/getAdminInfo")
    public String getAdminInfo(HttpServletRequest request,Model model){
        Admin admin=(Admin) request.getSession().getAttribute("admin");
        model.addAttribute("admin",admin);
        model.addAttribute("info",1);
        return "Admin_add";
    }

    /**
    * @Description:  (根据request中的session和提交的密码，对管理员密码进行更改，参数(session:request,密码:password))
    * @Param:  [request,password]
    * @return:  String
    * @Author: silence
    * @Date: 2020/4/4
    */
    @RequestMapping("/updateAdmin")
    public String updateAdmin(@RequestParam("password") String password, HttpServletRequest request, Model model){
        int result=adminService.updateAdmin(password,request);
        if(result==1){
            model.addAttribute("mes","用户信息变化，请重新登录！");
            return "Login";
        }

        else {
            return null;
        }
    }

}
