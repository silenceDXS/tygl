package com.thxy.tygl.service.ServiceImpl;

import com.thxy.tygl.entity.Circle;
import com.thxy.tygl.entity.Circle;
import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.mapper.CircleMapper;
import com.thxy.tygl.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class CircleServiceImpl implements CircleService {
    @Autowired
    CircleMapper circleMapper;

    public Map<String, Object> findAllCircle(int nowPage, int pageSize) {
        //获取所有用户
        List<Circle> circles=circleMapper.getAllCircleForPage((nowPage-1)*pageSize,pageSize);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("circle",circles);
        return resultMap;

    }

    @Override
    public int getTotal() {
        return circleMapper.getCountCircle();
    }


    public List<Circle> findCircleByStudentId(int nowPage, int pageSize, String studentId) {
        int currPage=(nowPage-1)*pageSize;
        int studentId1=Integer.parseInt(studentId);
        Map<String,Object> map=new HashMap<>();
        map.put("currPage",currPage);
        map.put("pageSize",pageSize);
        map.put("studentId",studentId1);
        List<Circle> result=circleMapper.findCircleByName(map);
        return result;
    }


    public int countFindCircle(String studentId) {
        int studentId1=Integer.parseInt(studentId);
        int result=circleMapper.countFindCircle(studentId1);
        return result;
    }

    @Override
    public int delCircleByStudentId(int param) {
        int result=circleMapper.delCircleByStudentId(param);
        return result;
    }

    @Override
    public Circle findCircleByCircleId(int circleId) {
        Circle circle=circleMapper.findCircleByCircleId(circleId);
        return circle;
    }
}
