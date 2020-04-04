package com.thxy.tygl.service.ServiceImpl;

import com.thxy.tygl.entity.Admin;
import com.thxy.tygl.entity.AdminJson;
import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.mapper.AdminMapper;
import com.thxy.tygl.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class AdminServicecImpl implements AdminService {

    /**
    * @Description:  (接收当前页码和页的尺寸，进行分页查询，参数(页码(nowPage),页的尺寸 :pageSize))
    * @Param:  [nowPage, pageSize]
    * @return:  HashMap
    * @Author: silence
    * @Date: 2020/4/4
    */
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Map<String, Object> findAllAdmin(int nowPage, int pageSize) {
        //获取所有用户
        List<Admin> admins=adminMapper.getAllAdminForPage((nowPage-1)*pageSize,pageSize);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("admin",admins);
        return resultMap;

    }


    /**
    * @Description:  (对管理员总数查询)
    * @Param:  []
    * @return:  int
    * @Author: silence
    * @Date: 2020/4/4
    */
    @Override
    public int getTotal() {
        return adminMapper.getCountAdmin();
    }


    /**
    * @Description:  (根据当前页码，页的尺寸和管理员真实姓名查询改管理员，参数(页码(nowPage),页的尺寸 :pageSize),姓名：name))
    * @Param:  [nowPage,name pageSize]
    * @return:  List[Admin]
    * @Author: silence
    * @Date: 2020/4/4
    */
    @Override
    public List<Admin> findAdminByName(int nowPage, int pageSize, String name) {
        int currPage=(nowPage-1)*pageSize;
        Map<String,Object> map=new HashMap<>();
        map.put("currPage",currPage);
        map.put("pageSize",pageSize);
        map.put("name",name);
        List<Admin> result=adminMapper.findAdminByName(map);
        return result;
    }

    /**
    * @Description:  (根据管理员姓名查询改管理员的账号总数,参数(姓名：name))
    * @Param:  [name]
    * @return: int
    * @Author: silence
    * @Date: 2020/4/4
    */
    @Override
    public int countFindAdmin(String name) {

        int result=adminMapper.countFindAdmin(name);
        return result;
    }

    /**
    * @Description:  (对管理员进行添加，参数(adminJson))
    * @Param:  [adminJson]
    * @return:  int
    * @Author: silence
    * @Date: 2020/4/4
    */
    @Override
    public int addAdmin(AdminJson adminJson) {
        Admin admin=new Admin();
        //生成当前时间
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(date);
        Timestamp timestamp=Timestamp.valueOf(time);

        admin.setAdminAccount(adminJson.getUsername());
        admin.setAdminPassword(adminJson.getPassword());
        admin.setName(adminJson.getName());
        admin.setLastTime(timestamp);
        int result=adminMapper.addAdmin(admin);

        return result;
    }

    /**
    * @Description:  (根据管理员账号查找改管理员，参数(账号:username))
    * @Param:  [username]
    * @return:  com.thxy.thgl.entity.Admin
    * @Author: silence
    * @Date: 2020/4/4
    */
    public Admin checkAdminByUserName(String username){

        Admin admin=adminMapper.checkAdminByName(username);
        return  admin;

    }

    /**
    * @Description:  (根据管理员id对其密码进行更改，参数(密码:password,id:request))
    * @Param:  [password,request]
    * @return:  int
    * @Author: silence
    * @Date: 2020/4/4
    */
    @Override
    public int updateAdmin(String password, HttpServletRequest request) {
        Admin admin=new Admin();
        Admin admin1=(Admin) request.getSession().getAttribute("admin");
        admin.setAdminId(admin1.getAdminId());
        admin.setAdminPassword(password);
        int result=adminMapper.updateAdmin(admin);
        return result;
    }


}
