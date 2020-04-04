package com.thxy.tygl.mapper;

import com.thxy.tygl.entity.Notice;
import com.thxy.tygl.entity.Type;
import com.thxy.tygl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {
    //分页查询
    List<Notice> getAllNoticeForPage(@Param("currPage") int nowPage, @Param("pageSize") int pageSize);
    //数据量查询
    int  getCountNotice();

    //查找用户
    List<Notice> findNoticeByTitle(@Param("param") Map<String,Object> map);

    //查找到的用户总量
    int countFindNotice(@Param("title") String title);

    //根据学号删除账号信息
    int delNoticeById(@Param("noticeId") int param);

    //添加公告
    int insertNotice(Notice notice);

    //根据noticeId查公告
    Notice findNoticeById(@Param("noticeId") int noticeId);

    //获取所有noticetype
    List<Type> getType();

}
