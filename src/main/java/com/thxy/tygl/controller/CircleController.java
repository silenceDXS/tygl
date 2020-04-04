package com.thxy.tygl.controller;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.entity.Circle;
import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.service.CircleService;

import com.thxy.tygl.service.CommentService;
import com.thxy.tygl.untils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Controller
public class CircleController {
    @Autowired
    CircleService circleService;

    @RequestMapping("/getAllCircle")
    public  String  getAllCircle(HttpServletRequest request, Model model){
        String pagenum=request.getParameter("nowPage");
        String param=request.getParameter("param");
        int nowPage=Integer.parseInt(pagenum);
        int pageSize=5;
        Map<String,Object> resultMap=circleService.findAllCircle(nowPage,pageSize);
        int total= circleService.getTotal();
        PageResult pageResult=new PageResult(total,pageSize,nowPage);
        pageResult.setFindParam(1);
        model.addAttribute("circle",resultMap.get("circle"));
        model.addAttribute("pageResult",pageResult);
        if(param.equals("1"))
            return  "Circle";
        else {
            return "Circle::pageCircle";
        }
    }
    /**
     * 根据标题查询公告信息
     * @param request
     * @param model
     * @return
     */

    @RequestMapping("/findCircle")
    public String  findNotice(HttpServletRequest request, Model model){
        String pagenum=request.getParameter("nowPage");
        String param=request.getParameter("param");
        List<Circle> circle=circleService.findCircleByStudentId(Integer.parseInt(pagenum),5,param);
        int total=circleService.countFindCircle(param);
        PageResult pageResult=new PageResult(total,5,Integer.parseInt(pagenum));
        pageResult.setFindParam(2);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("circle", circle);
        return "Circle::pageCircle";

    }
    /**
     * 根据studentId删除用户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/delCircle")
    public  String delCircleByStudentId(HttpServletRequest request,Model model){

        String param=request.getParameter("param");
        String nowPage=request.getParameter("nowPage");
        int nowPage1=Integer.parseInt(nowPage);
        int param1= Integer.parseInt(param);
        int result=circleService.delCircleByStudentId(param1);
        int total=circleService.getTotal();
        Map<String,Object> map=circleService.findAllCircle(nowPage1,5);
        PageResult pageResult=new PageResult(total,5,nowPage1);
        pageResult.setFindParam(1);
        model.addAttribute("circle",map.get("circle"));
        model.addAttribute("pageResult",pageResult);
        return  "Circle::pageCircle";

    }

    @RequestMapping("/getCircleContent")
    public String getCircleContent(HttpServletRequest request,Model model){
        String noticeId=request.getParameter("param");
        int noticeId1=Integer.parseInt(noticeId);
        Circle circle=circleService.findCircleByCircleId(noticeId1);
        model.addAttribute("circle",circle);
        model.addAttribute("param1","0");
        return "Notice_content";

    }
}
