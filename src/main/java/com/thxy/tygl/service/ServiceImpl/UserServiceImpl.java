package com.thxy.tygl.service.ServiceImpl;

import com.thxy.tygl.entity.User;
import com.thxy.tygl.mapper.UserMapper;
import com.thxy.tygl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     *
     * @param nowPage
     * @param pageSize
     * @return resultMap 存入所以user
     */
    @Override
    public Map<String,Object> findAllUser(int nowPage,int pageSize) {
        //获取所有用户
        List<User> users=userMapper.getAllUserForPage((nowPage-1)*pageSize,pageSize);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("users",users);
        return resultMap;
    }

    /**
     *
     * @return int 返回总记录数
     */
    public  int getTotal(){

        return userMapper.getCountUser();
    }




    public List<User> findUser(int nowPage, int pageSize,int param) {
        List<User> result=userMapper.findUser(0,5,param);
        return result;
    }

    @Override
    public int countFindUser(int studentId) {
        int result=userMapper.countFindUser(studentId);
        return result;
    }

    @Override
    public int delUserById(int param) {
        int result=userMapper.delUserById(param);
        return result;
    }


}
