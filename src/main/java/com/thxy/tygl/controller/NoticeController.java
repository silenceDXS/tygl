package com.thxy.tygl.controller;

import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.entity.NoticeJson;
import com.thxy.tygl.entity.Type;
import com.thxy.tygl.entity.User;
import com.thxy.tygl.service.NoticeService;
import com.thxy.tygl.untils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Controller
public class NoticeController {
    /**
     * 获取所有公告信息并分页
     */
    @Autowired
    NoticeService noticeService;
    @RequestMapping(value = "/getAllNotice",method = RequestMethod.GET)
    public String getAllNotice (Model model, HttpServletRequest request){
        String pagenum=request.getParameter("nowPage");
        String param=request.getParameter("param");
        int nowPage=Integer.parseInt(pagenum);
        int pageSize=5;
        Map<String,Object> resultMap=noticeService.findAllNotice(nowPage,pageSize);
        int total=noticeService.getTotal();
        PageResult pageResult=new PageResult(total,pageSize,nowPage);
        pageResult.setFindParam(1);
        model.addAttribute("notice",resultMap.get("notice"));
        model.addAttribute("pageResult",pageResult);
        if(param.equals("1"))
        return  "Notice_index";
        else {
            return "Notice_index::pageNotice";
        }
    }

    /**
     * 根据标题查询公告信息
     * @param request
     * @param model
     * @return
     */

    @RequestMapping("/findNotice")
    public String  findNotice(HttpServletRequest request, Model model){

        String param=request.getParameter("param");
        String type=request.getParameter("type");
        String nowPage=request.getParameter("nowPage");
        List<Notice> notice=noticeService.findNoticeByTitle(Integer.parseInt(nowPage),5,param);
        int total=noticeService.countFindNotice(param);
        PageResult pageResult=new PageResult(total,5,Integer.parseInt(nowPage));
        pageResult.setFindParam(2);
        pageResult.setInputParam(param);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("notice", notice);
        if(type.equals("1"))
        return "Notice_index::pageNotice";
        else {
            model.addAttribute("inputValue",param);
            return "Notice_index";
        }

    }

    /**
     * 根据公告id删除用户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/delNotice")
    public  String delUserById(HttpServletRequest request,Model model){
        String param=request.getParameter("param");
        String nowPage=request.getParameter("nowPage");
        int nowPage1=Integer.parseInt(nowPage);
        int param1= Integer.parseInt(param);
        int result=noticeService.delNoticeById(param1);
        int total=noticeService.getTotal();
        Map<String,Object> map=noticeService.findAllNotice(nowPage1,5);
        PageResult pageResult=new PageResult(total,5,nowPage1);
        pageResult.setFindParam(1);
        model.addAttribute("notice",map.get("notice"));
        model.addAttribute("pageResult",pageResult);
        return  "Notice_index::pageNotice";
    }

    @RequestMapping(value = "/noticeSub")
    public String noticeSub(NoticeJson noticeJson,Model model){
        int result=noticeService.insertNotice(noticeJson);
        model.addAttribute("data",1);
        return "Notice_release";
    }

    @RequestMapping(value = "/noticeContent")
    public String noticeContent(HttpServletRequest request,Model model){
        String noticeId=request.getParameter("param");
        int noticeId1=Integer.parseInt(noticeId);
        Notice notice=noticeService.findNoticeById(noticeId1);
        model.addAttribute("notice",notice);
        model.addAttribute("param1","1");
        return "Notice_content";
    }

    @RequestMapping("/getType")
    public  String getType(Model model){
        List<Type> result=noticeService.getType();
        model.addAttribute("type",result);
        return  "Notice_release";
    }

}
