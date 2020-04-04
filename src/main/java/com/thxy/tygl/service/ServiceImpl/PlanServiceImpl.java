package com.thxy.tygl.service.ServiceImpl;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.entity.Plan;
import com.thxy.tygl.mapper.PlanMapper;
import com.thxy.tygl.service.PlanService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanMapper planMapper;
    @Override
    public void addPlan(String pic) {
        Map<String,Object> map=new HashMap<>();
        map.put("pic",pic);
        map.put("active",1);
        planMapper.addPlan(map);
    }

    public Map<String,Object> getAllPlan(int nowPage,int pageSize){
        //获取所有用户
        List<Plan> plan=planMapper.getAllPlanForPage((nowPage-1)*pageSize,pageSize);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("plan",plan);
        return resultMap;

    }

    public  int getTotal(){
        return planMapper.getCountPlan();
    }

    @Override
    public List<Plan> findPlanByDate(int nowPage, int pageSize, String date) {
        int currPage=(nowPage-1)*pageSize;
        Map<String,Object> map=new HashMap<>();
        map.put("currPage",currPage);
        map.put("pageSize",pageSize);
        map.put("date",date);
        List<Plan> result=planMapper.findPlanByDate(map);
        return result;
    }

    @Override
    public int countFindPlan(String date) {
        int result=planMapper.countFindPlan(date);
        return result;
    }

    public int delPlanById(int param) {
        int result=planMapper.delPlanById(param);
        return result;
    }
}
