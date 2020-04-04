package com.thxy.tygl.controller;


import com.thxy.tygl.entity.Comment;
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
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/getAllComment")
    public  String  getAllComment(HttpServletRequest request, Model model){
        String pagenum=request.getParameter("nowPage");
        String circleId=request.getParameter("circleId");
        String param=request.getParameter("param");
        int circle1=Integer.parseInt(circleId);
        int nowPage=Integer.parseInt(pagenum);
        int pageSize=5;
        Map<String,Object> resultMap=commentService.findAllComment(nowPage,pageSize,circle1);
        int total= commentService.getTotal();
        PageResult pageResult=new PageResult(total,pageSize,nowPage);
        pageResult.setFindParam(1);
        model.addAttribute("comment",resultMap.get("comment"));
        model.addAttribute("pageResult",pageResult);
        if(param.equals("1"))
            return  "Comment";
        else {
            return "Comment::pageComment";
        }
    }
    /**
     * 根据标题查询公告信息
     * @param request
     * @param model
     * @return
     */

    @RequestMapping("/findComment")
    public String  findNotice(HttpServletRequest request, Model model){
        String param=request.getParameter("param");
        List<Comment> comment=commentService.findCommentByParam(1,5,param);
        int total=commentService.countFindComment(param);
        PageResult pageResult=new PageResult(total,5,1);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("comment", comment);
        return "Comment::pageComment";

    }
    /**
     * 根据studentId删除用户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/delComment")
    public  String delCommentByStudentId(HttpServletRequest request,Model model){

        String param=request.getParameter("param");
        String nowPage=request.getParameter("nowPage");
        String circleId1=request.getParameter("circleId");
        int nowPage1=Integer.parseInt(nowPage);
        int param1= Integer.parseInt(param);
        int circleId=Integer.parseInt(circleId1);
        int result=commentService.delCommentByCommentId(param1);
        int total=commentService.getTotal();
        Map<String,Object> map=commentService.findAllComment(nowPage1,5,circleId);
        PageResult pageResult=new PageResult(total,5,nowPage1);
        model.addAttribute("comment",map.get("comment"));
        model.addAttribute("pageResult",pageResult);
        return  "Comment::pageComment";

    }
}
