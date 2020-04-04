package com.thxy.tygl.controller;

import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.entity.Plan;
import com.thxy.tygl.service.PlanService;
import com.thxy.tygl.untils.FileSave;
import com.thxy.tygl.untils.PageResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.standard.expression.Token;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

@Controller
public class PlanController {
    @Autowired
    PlanService planService;


    @RequestMapping("/getAllPlan")
    public String getAllPlan(HttpServletRequest request, Model model){
        String param=request.getParameter("param");
        String pagenum=request.getParameter("nowPage");
        int nowPage=Integer.parseInt(pagenum);
        int pageSize=5;
        Map<String,Object> resultMap=planService.getAllPlan(nowPage,pageSize);
        int total= planService.getTotal();
        PageResult pageResult=new PageResult(total,pageSize,nowPage);
        pageResult.setFindParam(1);
        model.addAttribute("plan",resultMap.get("plan"));
        model.addAttribute("pageResult",pageResult);
        if(param.equals("1"))
            return  "Plan";
        else {
            return "Plan::pagePlan";
        }
    }

    @RequestMapping("/addPlan")
    public String addPlan(@RequestParam( "pic") MultipartFile[] file, Model model){
            for(MultipartFile f:file){
                String pic1= FileSave.upload(f,"E:\\java\\tygl\\src\\main\\resources\\static\\images\\tyglFilePlan\\");
                planService.addPlan(pic1);
            }
        model.addAttribute("mes","添加轮播图成功！");
        return "Plan_add_success";
    }

    @RequestMapping("/findPlan")
    public String findPlan(HttpServletRequest request,Model model){
        String param=request.getParameter("param");
        String pageNum=request.getParameter("nowPage");
        List<Plan> plan=planService.findPlanByDate(Integer.parseInt(pageNum),5,param);
        int total=planService.countFindPlan(param);
        PageResult pageResult=new PageResult(total,5,Integer.parseInt(pageNum));
        pageResult.setFindParam(2);
        model.addAttribute("pageResult",pageResult);
        model.addAttribute("plan", plan);
        return "Plan::pagePlan";
    }

    /**
     * 根据公告id删除用户信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/delPlan")
    public  String delUserById(HttpServletRequest request,Model model){
        String param=request.getParameter("param");
        String nowPage=request.getParameter("nowPage");
        int nowPage1=Integer.parseInt(nowPage);
        int param1= Integer.parseInt(param);
        int result=planService.delPlanById(param1);
        int total=planService.getTotal();
        Map<String,Object> map=planService.getAllPlan(nowPage1,5);
        PageResult pageResult=new PageResult(total,5,nowPage1);
        pageResult.setFindParam(1);
        model.addAttribute("plan",map.get("plan"));
        model.addAttribute("pageResult",pageResult);
        return  "Plan::pagePlan";
    }

}
